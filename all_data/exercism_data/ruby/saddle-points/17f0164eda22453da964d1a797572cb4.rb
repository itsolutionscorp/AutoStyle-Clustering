class Matrix

	def initialize(text)
		@text = text
	end

	def rows
		@text.split("\n").map do |single_row|
      single_row.split(' ').map(&:to_i)
    end
	end

	def columns
		rows.transpose
	end

	def saddle_points
		points = []
		rows.each_with_index do |row, x|
			row.each_with_index do |number, y|
				points << [x,y] if number >= row.max && number <= columns[y].min
			end
		end
		points
	end

end
