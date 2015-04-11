class Cipher

	DEFAULT_KEY = ('a'..'z').to_a.join

	def initialize(key = DEFAULT_KEY)
		raise ArgumentError unless key =~ /^[a-z]+$/
		@key = key.split('')
	end

	def key
		@key.join
	end

	def encode(message)
		code(message)
	end

	def decode(message)
		code(message)
	end

	private

		def code(message)
			method = calling_method(caller)
			message.chars.each_with_index.map { |char, i| 
				compare_letters(char, @key[i % @key.length], method)
			}.join	
		end

		def calling_method(prev)
			prev[0][/`([^']*)'/, 1]
		end

		def compare_letters(a, b, method)
			a, b = letter_value(a), letter_value(b)
			change = method == "encode" ? a + b : a - b
			letter_value_to_char change
		end

		def letter_value(char)
			char.ord - 'a'.ord
		end

		def letter_value_to_char(value)
			(value % 26 + 'a'.ord).chr
		end

end
