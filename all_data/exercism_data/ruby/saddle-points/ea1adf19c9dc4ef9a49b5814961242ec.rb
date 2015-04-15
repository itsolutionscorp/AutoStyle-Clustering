class Matrix
	attr_accessor :rows, :columns
	def initialize(string)
		@columns = Hash.new
		array = string.split("\n").map{|x| x.split(' ')}
		counter = (0..(array.length-1)).to_a
		@rows = Hash[counter.zip(array.map{|x| x.map{|y| y.to_i } })]
		colrows = @rows.values
		i = 0
		while i < @rows.values[0].length
			@columns[i] = colrows.map{|x| x[i]}
			i += 1
		end
	end
	
	def saddle_points
		col = @columns.values
		row = @rows.values
		saddle = []
		col.each_with_index do |c, c_index|
			row.each_with_index do|r, r_index|
				if c.min == r.max
					saddle << [r_index, c_index]
				end 
			end			
		end
		saddle
	end
end
