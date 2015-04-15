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
	
	def search_for(element)
		if 		@list[self.middle] == nil
				$index.clear
				raise RuntimeError.new
		elsif	@list[self.middle] == element
				$index = [] if $index == nil
					$index << self.middle
				index = if $index.length == 1
							$index[0]
						else $index.each.inject {|index, i| index + i + 1}
						end
				$index.clear
				return index
		elsif 	@list[self.middle] < element
				bin = BinarySearch.new(@list.drop(self.middle + 1))		
				$index = [] if $index == nil
				$index << self.middle
				bin.search_for(element)
		else 	bin = BinarySearch.new(@list.take(self.middle))
				bin.search_for(element)
		end
	end
	
	def middle
		$index_middle = (@list.length / 2).to_int      
	end
end
