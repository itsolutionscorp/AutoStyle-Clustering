class Say

	@@TRIPLET_WORDS = ["trillion", "billion", "million", "thousand", nil]

	def initialize(n)
		@n = n
	end

	def in_english
		raise ArgumentError if @n < 0 || @n >= 1000000000000 

		return "zero" if @n == 0

		thousands_chunks.zip(@@TRIPLET_WORDS).map { 
			|chunk, descriptor|
				Triplet.new(chunk, descriptor).in_english
		}.compact.join(" ").strip
	end
	

	private

		def thousands_chunks
			@n.to_s
			  .rjust(@@TRIPLET_WORDS.length*3, '0')
			  .scan(/.{1,3}/)
			  .map(&:to_i)
		end

		class Triplet
			attr_reader :n

			@@HUNDRED_WORDS = {
				1 => "one",   2 => "two",   3 => "three",
				4 => "four",  5 => "five",  6 => "six",
				7 => "seven", 8 => "eight", 9 => "nine",
			}

			def initialize(number, descriptor)
				@n = number
				@descriptor = descriptor
			end

			def in_english
				return nil if n == 0 
				
				[	hundreds(n/100), 
					Doublet.new(n%100).in_english,
					@descriptor						].compact.join(" ")
			end

			private

				def hundreds(digit)
					digit == 0 ? nil : @@HUNDRED_WORDS[digit] + " hundred"
				end

		end

		class Doublet
			attr_reader :n

			@@IRREGULARS = {
				  1 => "one",       2 => "two",      3 => "three",
				  4 => "four",      5 => "five",     6 => "six",
				  7 => "seven",     8 => "eight",    9 => "nine",
				 10 => "ten",      11 => "eleven",  12 => "twelve",
				 13 => "thirteen", 15 => "fifteen", 18 => "eighteen"
			}

			@@TENS = {
				1 => "$teen",     2 => "twenty-$",  3 => "thirty-$",
				4 => "forty-$",   5 => "fifty-$",   6 => "sixty-$",
				7 => "seventy-$", 8 => "eighty-$",  9 => "ninety-$",
			}	
			
			def initialize(number)
				@n = number
			end

			def in_english
				return nil if n == 0
					
				irregular? ? @@IRREGULARS[n] : build_word
			end

			private

				def irregular?
					@@IRREGULARS[n]
				end

				def build_word
					base = @@TENS[n/10]
					fill = @@IRREGULARS[n%10]

					multipleOfTen? ? base.delete('-$') : base.gsub("$", fill)
				end

				def multipleOfTen?
					n%10 == 0
				end
		end
end
