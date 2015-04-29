class Bst

	attr_accessor :data, :left, :right

	def initialize(number)
		@data = number
		@left = nil
		@right = nil
	end
	
	def insert(number)
		if number <= self.data 
			if self.left != nil
				self.left.insert(number)
			else
				self.left = Bst.new(number) 
			end
		else
			if self.right != nil
				self.right.insert(number)
			else
				self.right = Bst.new(number)
			end
		end
	end
	
	def collect(arr=[])
		arr << data
		if left
			left.collect(arr)
		end
		if right
			right.collect(arr)
		end
		arr = arr.sort
	end
	
	def each &blk
		self.collect.each &blk
	end

end
