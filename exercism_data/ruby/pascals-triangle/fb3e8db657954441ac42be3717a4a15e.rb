class Triangle
	attr_reader :rows

	def initialize(n)
		@rows = (1..n).each_with_object([]) do |i, rows|
			row = Array.new(i)
			row[0] = row[-1] = 1
			(1..(row.length-2)).each do |j|
				row[j] = rows[i-2][j-1] + rows[i-2][j]
			end
			rows << row
		end
	end
	
end
