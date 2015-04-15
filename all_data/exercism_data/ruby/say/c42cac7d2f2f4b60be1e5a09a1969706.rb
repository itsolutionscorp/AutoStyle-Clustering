class Say
	#First iteration
       SINGLE	= {
		0 => 'zero',
		1 => 'one',
		2 => 'two',
		3 => 'three',
		4 => 'four',
		5 => 'five',
		6 => 'six',
		7 => 'seven',
		8 => 'eight',
		9 => 'nine'
	}
       TENS = {
	       11 => 'eleven',
	       12 => 'twelve',
	       13 => 'thirteen',
	       14 => 'fourteen',
	       15 => 'fifteen',
	       16 => 'sixteen',
	       17 => 'seventeen',
	       18 => 'eigtheen',
	       19 => 'nineteen'
       }
       MULTIPLE = {
	       10 => 'ten',
	       20 => 'twenty',
	       30 => 'thirty',
	       40 => 'fourty',
	       50 => 'fifty',
	       60 => 'sixty',
	       70 => 'seventy',
	       80 => 'eigthy',
	       90 => 'ninety'
       }

       def initialize(number)
	       @number = number
       end

       def in_english
	       raise ArgumentError if @number < 0 || @number > 999_999_999_999
	       digits = @number.to_s.split(//).reverse.map{|x| x.to_i}
	       case
	       when (0..9).to_a.include?(@number)
		       SINGLE[@number]
	       when (11..19).to_a.include?(@number)
		       TENS[@number]
	       when @number%10 == 0 && @number < 100
		       MULTIPLE[@number]
	       when (21..99).to_a.include?(@number)
		       "#{MULTIPLE[digits[1]*10]}-#{SINGLE[digits[0]]}"
	       when @number > 99 && @number < 1000
		       result = "#{SINGLE[digits[2]]} hundred #{MULTIPLE[digits[1]*10]}-#{SINGLE[digits[0]]}"
		       result.gsub!(/zero/,"").gsub!("-","").strip! if digits.include?(0)
		       result
	       when @number >999 && @number < 10**6
		       result = "#{SINGLE[digits[3]]} thousand #{SINGLE[digits[2]]} hundred #{MULTIPLE[digits[1]*10]}-#{SINGLE[digits[0]]}"
		       result.gsub!(/zero/,"").gsub!("-","").gsub!(/hundred/,"").strip! if digits.include?(0)
		       result
	       when @number >= 10**6 && @number < 10**9
		       result = "#{SINGLE[digits[6]]} million #{SINGLE[digits[3]]} thousand #{SINGLE[digits[2]]} hundred #{MULTIPLE[digits[1]*10]}-#{SINGLE[digits[0]]}"
		       result.gsub!(/zero/,"").gsub!("-","").gsub!(/hundred/,"").gsub!(/thousand/,"").strip! if digits.include?(0)
		       result
	       else
		       result = "#{SINGLE[digits[9]]} billion #{SINGLE[digits[6]]} million #{SINGLE[digits[3]]} thousand #{SINGLE[digits[2]]} hundred #{MULTIPLE[digits[1]*10]}-#{SINGLE[digits[0]]}"
	       end
       end
end
