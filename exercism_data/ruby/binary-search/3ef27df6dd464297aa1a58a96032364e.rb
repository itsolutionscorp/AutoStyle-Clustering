class BinarySearch
	attr_reader :list

	def initialize(list)
		raise ArgumentError unless list.sorted?
		@list = list
	end

	def middle
		@list.length/2
	end
	
	def search_for(n)
		case n <=> @list[middle]
		when  0
			return middle
		when -1
			left, right = 0, middle - 1
		when  1
			left, right = middle + 1, @list.length - 1
		end

		raise RuntimeError if right < left

		left + self.class.new(@list[left..right]).search_for(n)
	end

end

module Enumerable
	def sorted?
		each_cons(2).all? { |a, b| (a <=> b) <= 0 }
	end
end
