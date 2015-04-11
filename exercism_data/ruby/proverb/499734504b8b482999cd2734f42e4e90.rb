class Proverb
  def initialize(*items, qualifier:  "")
  	@qualifier = qualifier
  	@proverb = generate_proverb(items)
  end

  def to_s
  	@proverb
  end

  private

  def generate_proverb(items)
    items.each_cons(2)
    	.map {|x, y| "For want of a #{x} the #{y} was lost." }
    	.push("And all for the want of a #{fixed_qualifier}#{items[0]}.")
    	.join("\n")
  end

  def fixed_qualifier
  	@qualifier + " " unless @qualifier.empty?
  end
end
