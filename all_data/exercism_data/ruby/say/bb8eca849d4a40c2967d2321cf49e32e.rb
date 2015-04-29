class Say
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
	       40 => 'forty',
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
	       when @number == 0
		       SINGLE[@number]
	       when (1..99).to_a.include?(@number)
		       double(digits[0,2])
	       when @number > 99 && @number < 1000
		       hundreds(digits[0,3])
	       when @number >999 && @number < 10**6
		       result = "#{hundreds(digits[3,6])} thousand" 
		       result << " #{hundreds(digits[0,3])}"  unless convert(digits[0,3]) == 0
		       result
	       when @number >= 10**6 && @number < 10**9
		       result = "#{hundreds(digits[6,9])} million"
		       result << " #{hundreds(digits[3,6])} thousand" unless convert(digits[3,3]) == 0 
		       result << " #{hundreds(digits[0,3])}" unless convert(digits[0,3]) == 0
		       result
	       else
		       result = "#{hundreds(digits[9,12])} billion"
		       result << " #{hundreds(digits[6,9])} million" unless convert(digits[6,3]) == 0
		       result << " #{hundreds(digits[3,6])} thousand" unless convert(digits[3,3]) == 0
		       result << " #{hundreds(digits[0,3])}" unless convert(digits[0,3]) == 0
		       result
	       end
       end

       private
       def double(arg)
	       number = convert(arg)
	       case
	       when (1..9).to_a.include?(number)
		       SINGLE[number]
	       when (11..19).to_a.include?(number)
		       TENS[number]
	       when number%10 == 0
		       MULTIPLE[number]
	       when (21..99).to_a.include?(number) 
		       "#{MULTIPLE[arg[1]*10]}-#{SINGLE[arg[0]]}"
	       else
		       ""
	       end
       end
       def hundreds(arg)
	       number = convert(arg)
	       result = "#{SINGLE[arg[2]]} hundred"
	       result << " #{double(arg[0,2])}"
	       result.gsub!(/zero/, "") if arg[2] == 0
	       result.gsub!(/hundred/, "") if convert(arg[1,2]) == 0
	       result.strip!
	       result
       end
       def convert(arg)
	       arg.reverse.join("").to_i
       end
end
