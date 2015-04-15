class Palindromes
	attr_reader :palindromes, :largest, :smallest, :max_factor, :min_factor

	def initialize(hash)
		@max_factor = hash[:max_factor]
		@min_factor = hash[:min_factor] || 1
	end

	def generate
		@palindromes = Hash.new{|h,k| h[k] = []}
		(@min_factor..@max_factor).each do |i|
			(@min_factor..i).each do |j|
					palindromes[i*j] << [i,j].sort if palindrome? (i*j)
			end
		end
	end

	def largest
		@palindromes.sort_by {|pal, factor| pal}.last
	end

	def smallest
		@palindromes.sort_by {|pal, factor| pal}.first
	end

	private
	
		def palindrome? (num)
			num.to_s == num.to_s.reverse
		end

end

class Array
	def value
		self[0]
	end

	def factors
		self[1]
	end
end
