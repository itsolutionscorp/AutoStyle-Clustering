class Proverb

	def initialize(*items)
		@qualifier = (items[-1].class == Hash ? items.pop[:qualifier] + " " : "")
		@items_array = items
	end

	def to_s
		proverb = ""
		(0...(@items_array.size-1)).each { |x| proverb += "For want of a #{@items_array[x]} the #{@items_array[x+1]} was lost.\n" }
		proverb += "And all for the want of a #{@qualifier}#{@items_array[0]}."
		proverb
	end

end
