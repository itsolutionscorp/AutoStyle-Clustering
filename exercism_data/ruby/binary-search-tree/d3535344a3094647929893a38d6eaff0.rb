class Bst 
	# This is the first iteration; insert and iteration doesn't function properly past the second level
	def initialize(number)
		@tree = Tree.new(number)
	end

	def insert(number)
		case
		when @tree.data < number && @tree.right == []
			@tree.right = Tree.new(number)
		when @tree.data >= number && @tree.left == []
			@tree.left = Tree.new(number)
		end	
	end
	
	def left
		@tree.left	
	end
	
	def right
		@tree.right	
	end
	
	def data
		@tree.data
	end
	
	class Tree
		attr_accessor :left, :right, :data
		def initialize(number)
			@data = number
			@left = []
			@right = []
		end
	end
end
