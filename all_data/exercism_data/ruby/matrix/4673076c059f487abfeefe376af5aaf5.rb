class Matrix
	attr_reader :rows, :columns

	def initialize(matrix)
		@rows = matrix.split("\n").collect{|s| s.split(' ').map(&:to_i)}

		@columns = Array.new(@rows[0].size){|_| []}
		@rows.each do |row| 
			row.each_with_index do |n, i| 
				@columns[i] << n
			end
		end
	end
end
