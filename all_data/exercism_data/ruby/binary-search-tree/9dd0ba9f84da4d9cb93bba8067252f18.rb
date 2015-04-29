class Bst
	attr_accessor :data, :left, :right

	def initialize(data)
		@data = data
	end

	def insert(value)
		insert_in_direction(value, which_direction?(value))
	end

	def each(&block)
		left.each(&block) if left
		block.call(data)
		right.each(&block) if right
	end

	private

		def which_direction?(value)
			value <= data ? :left : :right
		end

		def insert_in_direction(value, direction)
			if branch = send(direction)
				branch.insert(value)
			else
				send("#{direction}=", Bst.new(value))
			end
		end
end
