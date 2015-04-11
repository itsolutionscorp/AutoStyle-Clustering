class Palindromes
	def initialize(*args)
		hash =args[0]
		@max = hash[:max_factor]
		@min = hash[:min_factor]
		@min ||= 1
		@palindromes = []
	end

	def generate
		range = (@min..@max).to_a
		range.each do |x|
			range.reverse.each do |y|
				z = x * y
				@palindromes << z if z.to_s == z.to_s.reverse
			end
		end
		@palindromes.sort!
	end

	def largest
		@value = @palindromes[-1]
		self
	end

	def smallest
		@value = @palindromes[0]
		self
	end

	def value
		@value
	end

	def factors
		choices = (1..@value).select{|n| @value % n == 0}
		result = (choices.zip choices.reverse).each {|c| c.sort!}.uniq
		result.select{ |factors| factors.all? {|limit| limit > (@min-1) && limit < (@max+1)}}	
	end
end
