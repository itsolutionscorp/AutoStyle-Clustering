class Proverb

	def initialize *args, qualifier: nil
    @items = args
    @qualifier = qualifier
  end

  def to_s

    (0..@items.size-2).each_with_object("") do |i,str|
      str << "For want of a #{@items[i]} the #{@items[i+1]} was lost.\n"
    end << "And all for the want of a #{@qualifier ? @qualifier+' '+@items[0] : @items[0]}."

  end

end
