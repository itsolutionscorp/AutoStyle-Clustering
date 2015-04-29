class Proverb
  def self.new(*items, qualifier: "")
  	qualifier += " " unless qualifier.empty?
  	causes_and_effects = items[0..-2].zip(items[1..-1])
  	causes_and_effects.map {|x, y| "For want of a #{x} the #{y} was lost.\n" }
  	  .push("And all for the want of a #{qualifier}#{items[0]}.")
  	  .join
  end
end
