class Triangle
	attr_reader :rows
	
	def initialize(height)
		raise(ArgumentError) if height < 1
		@rows = [[1]]
		(1...height).each do |i|
			@rows << [1] + @rows[i-1].each_cons(2).map{|a| a.reduce(:+)} + [1]
		end
	end
end
