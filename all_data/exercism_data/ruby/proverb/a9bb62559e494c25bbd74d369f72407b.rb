class Proverb
  def initialize(*items, qualifier:  "")
  	@proverb = generate_proverb(items, qualifier)
  end

  def to_s
  	@proverb
  end

  private

  def generate_proverb(items, qualifier)
  	qualifier += " " unless qualifier.empty?
    
    items[0..-2]
      .zip(items[1..-1])
      .map {|x, y| "For want of a #{x} the #{y} was lost.\n" }
      .push("And all for the want of a #{qualifier}#{items[0]}.")
      .join
  end
end
