class Triangle
	attr_reader :rows

	def initialize(n)
		@rows = (0...n).each_with_object([]) do |i, rows|
			rows << build_row(i+1, rows[i-1])
		end
	end

	private

		def build_row(size, prev_row)
			row = Array.new(size)
			row.each_index.map do |i|
				if first_or_last_index?(i, row)
					1
				else
					prev_row[i-1] + prev_row[i]
				end
			end
		end

		def first_or_last_index?(i, row)
			i == 0 || i == row.length - 1
		end
	
end
