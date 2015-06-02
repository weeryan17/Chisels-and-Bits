
package mod.chiselsandbits.chiseledblock.data;

import net.minecraftforge.common.property.IUnlistedProperty;


public final class UnlistedBlockFlags implements IUnlistedProperty<Integer>
{
	@Override
	public String getName()
	{
		return "f";
	}

	@Override
	public boolean isValid(
			final Integer value )
	{
		return true;
	}

	@Override
	public Class<Integer> getType()
	{
		return Integer.class;
	}

	@Override
	public String valueToString(
			final Integer value )
	{
		return Integer.toString( value );
	}
}