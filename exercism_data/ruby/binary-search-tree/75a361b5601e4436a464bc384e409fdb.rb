class Bst
	attr_reader :data, :left, :right

	def initialize(data)
		@data = data
		@left = nil
		@right = nil
	end

	def insert(new_data)
		if new_data <= @data
			if @left
				@left.insert new_data
			else
				@left = self.class.new new_data
			end
		else
			if @right
				@right.insert new_data
			else
				@right = self.class.new new_data
			end
		end
	end

	def each(&block)
		traverse.each(&block)
	end

	def traverse
		result = []
		result += @left.traverse if @left
		result << @data
		result += @right.traverse if @right
		result
	end
end
