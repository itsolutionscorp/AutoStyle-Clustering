class Triangle
	attr_reader :rows

	def initialize(n)
		@rows = (1..n).each_with_object([]) do |i, rows|
			row = Array.new(i)
			row[0] = row[-1] = 1
			prev_row = rows[i-2]
			row[1..-2].each_with_index do |_, j|
				row[j] = prev_row[j-1] + prev_row[j]
			end
			rows << row
		end
	end
	
end
