class Matrix
	
	def initialize(string)
		@matrix = []
		string.split("\n").each_with_index do |row, i|
			this_row = []
			row.to_s.split(' ').each do |element|
				this_row << element.to_i
			end
			@matrix << this_row
		end
		@matrix 
	end
	
	def rows
		@matrix
	end
	
	def columns
		columns = []
		for i in 0..(@matrix[0].length - 1)
			this_column = []
			for j in 0..(@matrix.length - 1)
				this_column << @matrix[j][i]
			end
			columns << this_column		
		end
		columns
	end
	
	def saddle_points
		saddle_points = []		
		@matrix.each_with_index do |row, index_row|
			row.each_with_index do |element, index_column|	 
				if element == row.max && element == self.columns[index_column].min
				saddle_points << [index_row, index_column] 
				end
			end
		end	
		saddle_points
	end
end
