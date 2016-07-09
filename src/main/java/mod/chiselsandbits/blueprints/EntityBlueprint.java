package mod.chiselsandbits.blueprints;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityBlueprint extends Entity
{

	final static int ITEMSTACK_WATCHER = 10;

	private ItemStack item;

	public EntityBlueprint(
			final World worldIn )
	{
		super( worldIn );
	}

	@Override
	public boolean hitByEntity(
			final Entity entityIn )
	{
		if ( !worldObj.isRemote )
		{
			worldObj.spawnEntityInWorld( new EntityItem( worldObj, posX, posY, posZ, item ) );
			setDead();
		}

		return false;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public boolean isPushedByWater()
	{
		return false;
	}

	@Override
	public boolean handleWaterMovement()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		getDataWatcher().addObjectByDataType( ITEMSTACK_WATCHER, 5 /* ITEMSTACK */ );
		setEntityBoundingBox( getEntityBoundingBox() );
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox()
	{
		return new AxisAlignedBB( -0.5, -0.5, -0.5, 0.5, 0.5, 0.5 ).offset( posX, posY, posZ );
	}

	@Override
	protected void readEntityFromNBT(
			final NBTTagCompound tagCompund )
	{
		setItemStack( ItemStack.loadItemStackFromNBT( tagCompund.getCompoundTag( "item" ) ) );
	}

	@Override
	protected void writeEntityToNBT(
			final NBTTagCompound tagCompound )
	{
		final NBTTagCompound itemNBT = new NBTTagCompound();
		item.writeToNBT( itemNBT );
		tagCompound.setTag( "item", itemNBT );
	}

	public ItemStack getItemStack()
	{
		return item != null ? item : getDataWatcher().getWatchableObjectItemStack( ITEMSTACK_WATCHER );
	}

	public void setItemStack(
			final ItemStack copy )
	{
		item = copy;
		getDataWatcher().updateObject( ITEMSTACK_WATCHER, item );
	}

	float age = 0;

	@Override
	public void onUpdate()
	{
		++age;
	}

	public float getRotation()
	{
		return age;
	}

}
