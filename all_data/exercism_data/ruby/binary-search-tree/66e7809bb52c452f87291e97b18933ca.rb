class Bst 
	attr_accessor :left, :right, :data

	def initialize(data)
		@data = data
		@left = NullObject.new
		@right = NullObject.new
	end

	def insert(number)
		if number > @data
			in_right(number)
		elsif number <= @data
			in_left(number)
		end
	end
	
	def each(&block)
		left.each(&block) 
		block.call(data)
		right.each(&block) 
	end
	
	private
	def in_right(number)
		right.insert(number) || @right = Bst.new(number)
	end

	def in_left(number)
		left.insert(number) || @left = Bst.new(number)
	end	
end
class NullObject

	def data
		nil
	end

	def insert(number)
		false
	end

	def each(&block)
	       false	
	end
end
