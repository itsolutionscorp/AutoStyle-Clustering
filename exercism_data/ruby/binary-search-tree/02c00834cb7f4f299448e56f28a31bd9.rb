class Bst 
	attr_accessor :left, :right, :data

	def initialize(data)
		@data = data
	end

	def insert(number)
		if number > @data
			in_right(number)
		elsif number <= @data
			in_left(number)
		end
	end

	def in_right(number)
		if right
			right.insert(number)
		else
			@right = Bst.new(number)
		end
	end

	def in_left(number)
		if left
			left.insert(number)
		else 
			@left = Bst.new(number)
		end
	end

	def each(&block)
		left.each(&block) if left
		block.call(data)
		right.each(&block) if right
	end
end
