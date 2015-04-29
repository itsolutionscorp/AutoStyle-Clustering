class Series 
	def initialize(string)
		@string = string
	end
	
	def slices(number)
		raise ArgumentError if number > @string.length
		@string.split(//).map{|x| x.to_i}.each_cons(number).to_a
	end
end
