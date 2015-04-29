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
		cls = @columns.values.map{|x| x.min}
		rws = @rows.values.map{|x| x.max}
		value = (cls & rws)
		saddle = []
		return [] if value == [] 
		value.each do |v|
			row.each_with_index do|r, rindex|
				col.each_with_index do|c, cindex|
					if r.include?(v) && c.include?(v)
						saddle << [rindex,cindex] 
					end
				end
			end
		end
		saddle
	end
end
