class Proverb
  def initialize(*items, qualifier:  "")
  	@proverb = generate_proverb(items, qualifier)
  end

  def to_s
  	@proverb
  end

  private

  def generate_proverb(items, qualifier)
    items.each_cons(2)
    	.map {|lacked_item, jeopardised_item| "For want of a #{lacked_item} the #{jeopardised_item} was lost." }
    	.push("And all for the want of a #{qualifier} #{items[0]}.".squeeze(" "))
    	.join("\n")
  end

end
