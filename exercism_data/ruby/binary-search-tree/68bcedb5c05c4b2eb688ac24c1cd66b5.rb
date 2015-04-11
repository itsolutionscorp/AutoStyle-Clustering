class Bst

	attr_accessor :data, :left, :right

	def initialize(val=nil)
		@data = val
		@left = nil
		@right = nil
	end

	def insert(val)
		insert_value(self, val)
	end

	def insert_value(node, val)
		if node.data.nil?
			node.data = val
		elsif val <= node.data
			node.left.nil? ? node.left = Bst.new(val) : insert_value(node.left, val)
		elsif val > node.data
			node.right.nil? ? node.right = Bst.new(val) : insert_value(node.right, val)
		end
	end

	def each(&block)
		all.each { |data| yield data }
	end

	protected

	def all
		result = []
		result += left.all if left
		result += [data]
		result += right.all if right
		
		result
	end

end
