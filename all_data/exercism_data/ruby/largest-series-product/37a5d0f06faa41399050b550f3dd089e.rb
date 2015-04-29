class Series
	attr_reader :digits
	
	def initialize(text)
		@digits = text.chars.map(&:to_i)
	end
	
	def slices(n)
		@digits.each_cons(n).to_a
	end
	
	def largest_product(n)
		return 1 if @digits.empty? && n==0
		raise(ArgumentError) if n > @digits.size
		slices(n).collect{|array| array.reduce(:*)}.max
	end
end
