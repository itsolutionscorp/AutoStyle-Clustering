class Matrix
	attr_reader :rows, :columns

	def initialize(matrix)
		@rows = matrix.split("\n").collect{|s| s.split(' ').map(&:to_i)}
		@columns = @rows.transpose
	end
end
