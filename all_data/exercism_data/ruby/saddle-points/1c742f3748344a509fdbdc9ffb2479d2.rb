class Matrix
	attr_accessor :rows, :columns, :saddle_points

	def initialize(matrix)
		@rows = matrix.lines.map { |line| line.split.map(&:to_i) }
		@columns = @rows.transpose
		@saddle_points = determine_saddle_points
	end

	private

		def determine_saddle_points
			rows.length.times.with_object([]) do |row, saddle_points|
				columns.length.times do |column|
					saddle_points << [row, column] if saddle_point?(row, column)
				end
			end
		end

		def saddle_point?(row, column)
			n = rows[row][column]
			n == rows[row].max && n == columns[column].min
		end
	
end
