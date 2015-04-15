class BinarySearch

	def initialize(list)
		raise ArgumentError.new if list.sort != list
		@list = list
	end
	
	def list
		@list
	end
	
	def search_for(element)
		raise RuntimeError.new unless @list.include?(element)
		if 		@list[self.middle] == element
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
