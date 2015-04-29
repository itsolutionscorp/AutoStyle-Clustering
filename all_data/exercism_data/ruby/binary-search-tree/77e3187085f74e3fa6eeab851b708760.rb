class Bst
	attr_reader :data
	
	def initialize(value)
		@data = value
		@branches = [nil, nil]
	end
	
	def left
		@branches.first
	end
	
	def right
		@branches.last
	end
	
	def insert(value)
		handle_insertion(value<=data ? 0 : 1, value)
	end
	
	def each(&block)
		self.left.each(&block) unless self.left.nil?
		block.call(self.data)
		self.right.each(&block) unless self.right.nil?
	end
	
private
	def handle_insertion(where, value)
		if @branches[where].nil?
			@branches[where] = Bst.new(value)
		else
			@branches[where].insert(value)
		end
	end
end
