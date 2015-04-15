class Palindromes
	
	Palindrome = Struct.new(:value, :factors)

	def initialize(options)
		@max_factor = options[:max_factor]
		@min_factor = options[:min_factor] || 1
	end
	
	def generate
		range = (@min_factor..@max_factor).to_a
		
		@list = range.product(range).map(&:sort).uniq.group_by do |pair|
			pair.reduce(:*)
		end.select { |value, factors| palindrome?(value) }
	end
	
	def largest
		select :max
	end
	
	def smallest
		select :min
	end
	
private

	def palindrome?(number)
		number.to_s == number.to_s.reverse
	end
	
	def select(operation)
		key = @list.keys.send(operation)
		selected = @list.find { |k, v| k == key }
		Palindrome.new(selected[0], selected[1])
	end
end
