class Proverb

	def initialize(*items)
		@items_array = items
		has_qualifier?
	end

	def to_s
		string = ""
		(0...(@items_array.size-@has_qualifier)).each do |x|
			string += "For want of a #{@items_array[x]} the #{@items_array[x+1]} was lost.\n"
		end
		string += "And all for the want of a " + (@has_qualifier == 1 ? "#{@items_array[0]}." : "#{@items_array[-1]} #{@items_array[0]}.")
		string
	end

	private

	def has_qualifier?
		@has_qualifier = 1 		# 1 == no, 2 == yes
		if @items_array[-1].class == Hash
			@has_qualifier = 2
			@items_array[-1] = @items_array[-1][:qualifier]
		end
	end

end
