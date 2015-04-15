class BinarySearch
	attr_reader :list
	
	def initialize(list)
		list.each_cons(2){|a, b| raise(ArgumentError, 'List not sorted!') if a > b}
		@list = list
	end
	
	def middle
		list.size / 2
	end
	
	def search_for(data)
		left = 0
		right = list.size - 1
		while left <= right
			pivot = (left + right) / 2
			
			case data <=> @list[pivot]
			when -1
				right = pivot - 1
			when 0
				return pivot
			when 1
				left = pivot + 1
			end
		end
		raise(RuntimeError, 'Data not found!')
	end
end
