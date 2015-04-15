class Matrix
	attr_reader :rows, :columns, :saddle_points

	def initialize(matrix)
		@rows = matrix.split("\n").collect{|s| s.split(' ').map(&:to_i)}
		@columns = @rows.transpose

		@saddle_points = []
		@rows.each_with_index do |row, r|  
			max = row.max
			row.each_with_index do |value, c|
				@saddle_points << [r, c] if value == max && value == @columns[c].min
			end
		end
	end
end
