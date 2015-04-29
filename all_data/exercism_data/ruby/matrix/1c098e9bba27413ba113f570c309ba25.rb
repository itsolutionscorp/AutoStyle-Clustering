class Matrix
	def initialize(matrix_string)
		@matrix = matrix_string.split("\n").map do |row|
			row.split.map(&:to_i)
		end
	end
	
	def rows
		@matrix
	end
	
	def columns
		@matrix.transpose
	end
end
