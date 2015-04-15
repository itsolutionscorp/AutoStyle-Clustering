class Proverb
	def initialize(*items, qualifier: nil)
		@items = items
		@qualifier = qualifier
	end

	def to_s()
		[].tap do |output|
			@items.each_with_index do |item, index|
				if index == @items.size - 1
					output << "And all for the want of a %s%s." % [(@qualifier) ? "#{@qualifier} " : '', @items[0]]
				else
					output << "For want of a #{@items[index]} the #{@items[index+1]} was lost."
				end
			end
		end.join("\n")
	end
end
