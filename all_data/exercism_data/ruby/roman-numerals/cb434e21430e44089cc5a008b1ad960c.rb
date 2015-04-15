class Fixnum

	@@roman_numeral_hash = {1000 => "M", 900 => "CM", 500 => "D", 400 => "CD", 100 => "C", 90 => "XC", 50 => "L", 40 => "XL", 10 => "X", 9 => "IX", 5 => "V", 4 => "IV", 1 => "I"}

	@@edge_case_hash = {500 => 900, 100 => 400, 50 => 90, 10 => 40, 5 => 9, 1 => 4} #each numerals has an edge except 1000

	def iterate_roman_numerals(key, value)
		check_edge_cases(key)
		while @num_integer >= key
			@roman_numeral_string += value
			@num_integer -= key
		end
	end

	def check_edge_cases(key)
		if @@edge_case_hash[key]
			if @num_integer - @@edge_case_hash[key] >= 0
				 @roman_numeral_string += @@roman_numeral_hash[@@edge_case_hash[key]]
				 @num_integer - @@edge_case_hash[key]
			end
		end
	end

	def to_roman
		@num_integer = self
		@roman_numeral_string = ""
		@@roman_numeral_hash.each_pair do |key, value|
			iterate_roman_numerals(key, value)
		end
		return @roman_numeral_string
	end

end

#QUESTIONS
#is this called monkeypatching?

#THOUGHTS
#This is not a permanent change to the Fixnum class. It seems that this this new method that is being added will only occur at runtime.

#Refactored the code to abstract logic 
