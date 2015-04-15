class Fixnum
	def to_roman
		table = {1 => 'I', 5 => 'V', 10 => 'X', 50 => 'L', 100 => 'C', 500 => 'D', 1000 => 'M' }
		result = String.new 
		number = self
		(number.to_s.size - 1).downto(0) do |i|
			case number / (10 ** i)
			when 1
				result += table[10 ** i]
			when 2
				result += table[10 ** i] * 2
			when 3
				result += table[10 ** i] * 3
			when 4
				result += table[10 ** i] + table[5 * (10**i)]
			when 5
				result += table[5 * (10 ** i)]
			when 6
				result += table[5 * (10 ** i)] + table[10 ** i]
			when 7
				result += table[5 * (10 ** i)] + table[10 ** i] * 2
			when 8
				result += table[5 * (10 ** i)] + table[10 ** i] * 3
			when 9
				result += table[10 ** i] + table[10 * (10 ** i)]
			end
			number %= 10 ** i
		end
		result
	end
end
