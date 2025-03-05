package pe.senati

interface OnClickListener
{
    fun onClick(commerce: CommerceEntity)
    fun onFavoriteCommerce(commerceEntity: CommerceEntity)
    fun onDeleteCommerce(commerceEntity: CommerceEntity)
}