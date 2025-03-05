package pe.senati

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import pe.senati.databinding.ActivityMainBinding
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), OnClickListener   {

    //objetos declarados
    private lateinit var mBinding:ActivityMainBinding //referencia a activity_main.xml (vista principal)
    private lateinit var mAdapter: CommerceAdapter
    private lateinit var mGridLayoutManager:GridLayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         */
        mBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //evento btnGuardar
        mBinding.btnGuardar.setOnClickListener {
            val commerce= CommerceEntity(name=mBinding.etNameCommerce.text.toString().trim())

            //permite realizar dos procesos o mas se ejecutan simulteanamente a la vez con thread(hilos), con Thread y el de la lista  mAdapter.addCommerce(commerce) se ejecutan ala vez
                //se va ala base de datos (database)
                Thread {
                    CommerceApplication.database.commerceDao().insert(commerce)
                }.start()
                // se va alista (memory)
                mAdapter.insertMemory(commerce)
        }

        //call method
        this.loadRecyclerView()

    }


    override fun onClick(commerceEntity: CommerceEntity) {
        //code..
    }

    override fun onFavoriteCommerce(commerceEntity: CommerceEntity) {
        //code..
        commerceEntity.isFavorite=!commerceEntity.isFavorite

        val queue = LinkedBlockingQueue<Int>()
        Thread {
            CommerceApplication.database.commerceDao().update(commerceEntity)
        }.start()

        mAdapter.updateMemory(commerceEntity)
    }
    override fun onDeleteCommerce(commerceEntity: CommerceEntity){
        Thread {
            CommerceApplication.database.commerceDao().delete(commerceEntity)
        }.start()

        mAdapter.deleteMemory(commerceEntity)
    }







    private fun loadRecyclerView()
    {
        mAdapter=CommerceAdapter(mutableListOf(),this)

        // algo parecido a un paginado, la cantidad de tarjeta que se aparecera 1 por fila
        mGridLayoutManager= GridLayoutManager(this,1)
this.loadCommerces()
        mBinding.recycleView.apply {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager=mGridLayoutManager
        }
    }



    private fun loadCommerces()
    {
        val queue = LinkedBlockingQueue<MutableList<CommerceEntity>>()

        Thread {
            val commerces = CommerceApplication.database.commerceDao().findAll()
            queue.put(commerces)
        } .start()

        mAdapter.findAllMemory(queue.take())
    }


}