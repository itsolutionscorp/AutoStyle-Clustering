class Matrix
	attr_accessor :rows, :columns

	def initialize(matrix)
		@rows = matrix.lines.map { |line| line.split.map(&:to_i) }
		@columns = @rows.transpose
	end
	
end
