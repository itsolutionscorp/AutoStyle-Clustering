class Proverb
  def initialize(*items, qualifier:  "")
  	@items = items
    @qualifier = qualifier
  end

  def to_s
  	generate_proverb(@items, @qualifier)
  end

  private

  def generate_proverb(items, qualifier)
    items.each_cons(2)
    	.map {|lacked_item, jeopardised_item| "For want of a #{lacked_item} the #{jeopardised_item} was lost." }
    	.push("And all for the want of a #{qualifier} #{items.first}.".squeeze " ")
    	.join("\n")
  end

end
