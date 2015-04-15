class BinarySearch
	attr_reader :list

	def initialize(list)
		raise ArgumentError unless list.sorted?
		@list = list
	end
	
	def search_for(n)
		binary_search(n, 0, @list.size-1)
	end

	def middle
		@list.length/2
	end

	private

		def binary_search(n, left, right)
			middle = (right - left)/2 + left
			
			case n <=> @list[middle]
			when  0 then return  middle
			when  1 then left  = middle + 1
			when -1 then right = middle - 1
			end

			raise RuntimeError if right < left

			binary_search(n, left, right)
		end

end

module Enumerable
	def sorted?
		each_cons(2).all? { |a, b| (a <=> b) <= 0 }
	end
end
