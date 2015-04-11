class Say
	SAY_TWENTY = 
						  ["nineteen", "eighteen", "seventeen", "sixteen", "fifteen", "fourteen", 
						   "thirteen", "twelve", "eleven", "ten", "nine", "eight", "seven", "six", 
						   "five", "four", "three", "two", "one", "zero"]
	SAY_TENS  = ["ninety", "eighty", "seventy", "sixty", "fifty", "forty", "thirty", "twenty"]
	SAY_LARGE = ["billion", "million", "thousand", "hundred"]

	ZERO_TWENTY = Hash[(19.downto(0)).zip(SAY_TWENTY)]
	TENS 				= Hash[(20..90).step(10).to_a.reverse.zip(SAY_TENS)]
	LARGE 			= Hash[[10**9, 10**6, 1000, 100].zip(SAY_LARGE)]

	def initialize (num)
		raise ArgumentError unless (0...10**12).include?(num)
		@num = num
	end

	def in_english
    convert(@num)
  end

  private

	  def convert(num)
	    return convert_to_20(num) if num < 20
	    return convert_to_100(num) if num < 100
	    convert_large(num)
	  end

	  def convert_to_20(num)
	    ZERO_TWENTY[num]
	  end

	  def convert_to_100(num)
	  	convert_general(num, TENS) { |word| word }
	  end

	  def convert_large(num)
	  	convert_general(num, LARGE) do |word, quotient|
	  		convert(quotient) + " " + word
	  	end
	  end

	  def convert_general(num, list, &block)
	  	words = []
	    list.each do |value, word|
	      quotient, num = num.divmod(value)
	      words << yield(word, quotient) if quotient > 0
	    end
	    words << convert(num) if num > 0
	    SAY_TENS.include?(words[0]) ? words.join("-") : words.join(" ")
	  end
end
