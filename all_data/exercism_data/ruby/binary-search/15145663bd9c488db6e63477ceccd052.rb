class BinarySearch
	attr_reader :list

	def initialize(list)
		raise ArgumentError unless list.sort == list
		@list = list
	end
	
	def search_for(number)
		raise RuntimeError unless @list.include?(number)
		case number <=> @list[middle]
		when 0
			middle
		when -1
			BinarySearch.new(@list[0...middle]).search_for(number)
		when 1
			middle + 1 + BinarySearch.new(@list[middle+1..-1]).search_for(number)
		end
	end
	
	def middle
		@list.size / 2
	end
end
