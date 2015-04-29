class BinarySearch

	def initialize(list)		
		list.each_with_index do |element, i|
			if i < list.length - 2
			raise ArgumentError.new if element >= list[i + 1]
			end
		end		
		@list = list
	end
	
	def list
		@list
	end
	
	def search_for(element, index = [])
		if 		@list[self.middle] == nil
				raise RuntimeError.new
		elsif	@list[self.middle] == element
				index << self.middle
				position = 	if index.length == 1
							 index[0]
							else index.each.inject {|position, i| position + i + 1}
							end
				return position
		elsif 	@list[self.middle] < element
				bin = BinarySearch.new(@list.drop(self.middle + 1))		
				index << self.middle
				bin.search_for(element, index)
		else 	bin = BinarySearch.new(@list.take(self.middle))
				bin.search_for(element, index)
		end
	end
	
	def middle
		index_middle = (@list.length / 2).to_int      
	end
end
