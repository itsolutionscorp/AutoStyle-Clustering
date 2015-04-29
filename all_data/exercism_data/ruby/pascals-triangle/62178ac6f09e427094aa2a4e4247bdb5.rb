class Triangle
	attr_reader :rows
	
	def initialize(rows)
		@rows = create(rows)
	end
	
private

	def next_row(triangle)
		return [1] if triangle.empty?
		[0, *triangle[-1], 0].each_cons(2).map do |pair|
			pair.reduce(:+)
		end
	end

	def create(rows)
		(1..rows).to_a.inject([]) do |triangle, row_num|
			triangle << next_row(triangle)
		end
	end
end
