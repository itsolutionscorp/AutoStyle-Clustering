class Bst 
	attr_accessor :left, :right, :data

	def initialize(data)
		@data = data
	end

	def insert(number)
		case 
		when number > @data
			in_right(number)
		when number < @data || number == @data
			in_left(number)
		else
			raise ArgumentError
		end
	end

	def in_right(number)
		case
		when right
			right.insert(number)
		else
			@right = Bst.new(number)
		end
	end

	def in_left(number)
		case
		when left
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
