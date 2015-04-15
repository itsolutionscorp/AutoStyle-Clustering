class Bst
	attr_reader :data, :left, :right

	def initialize (num)
		@data = num
	end

	def insert (num)
		if num <= data
			left ? left.insert(num) : @left = Bst.new(num)
		else
			right ? right.insert(num) : @right = Bst.new(num)
		end
	end

	def each(&block)
		left.each {|x| yield x } if left
		yield data
		right.each {|x| yield x } if right
	end
end
