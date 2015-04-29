class Matrix
	def initialize(matrix_string)
		@matrix = matrix_string.split("\n").map do |row|
			row.split.map(&:to_i)
		end
		@rows = rows.length
		@cols = columns.length
	end
	
	def rows
		@matrix
	end
	
	def columns
		@matrix.transpose
	end
	
	def saddle_points
		[*0...@rows].product([*0...@cols]).select do |coords|
			saddle_point?(*coords)
		end
	end

private
	def	saddle_point?(row, col)
		(@matrix[row][col] == rows[row].max) && 
		(@matrix[row][col] == columns[col].min)
	end
end
