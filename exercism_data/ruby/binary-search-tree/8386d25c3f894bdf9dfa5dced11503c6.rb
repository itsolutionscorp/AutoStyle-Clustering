class Bst
	attr_reader :data
	LEFT = 0
	RIGHT = 1
	
	def initialize(value)
		@data = value
		@branches = [nil, nil]
	end
	
	def left
		@branches[LEFT]
	end
	
	def right
		@branches[RIGHT]
	end
	
	def insert(value)
		handle_insertion(value<=data ? LEFT : RIGHT, value)
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
