class Matrix
	attr_accessor :rows, :columns
	def initialize(string)
		@columns = Hash.new
		array = string.split("\n").map{|x| x.split(' ')}
		counter = (0...array.length).to_a
		@rows = Hash[counter.zip(array.map{|x| x.map{|y| y.to_i } })]
		colrows = @rows.values
		i = 0
		number_of_columns = @rows.values[0].length
		while i < number_of_columns
			@columns[i] = colrows.map{|x| x[i]}
			i += 1
		end
	end
end
