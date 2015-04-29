class Say

  NumberLookup = { 
       0 =>       "zero" ,  1 =>       "one" ,    2 =>    "two" , 3 => "three" ,
       4 =>       "four" ,  5 =>      "five" ,    6 =>    "six" , 7 => "seven" ,
       8 =>      "eight" ,   9 =>     "nine" , 10 =>      "ten" ,
       11 =>    "eleven" ,  12 =>   "twelve" , 13 => "thirteen" ,
       14 =>  "fourteen" ,  15 =>  "fifteen" , 16 =>  "sixteen" ,
       17 => "seventeen" ,  18 => "eighteen" , 19 => "nineteen" ,
       20 =>    "twenty" ,  30 =>   "thirty" , 40 =>   "forty"  ,
       50 =>     "fifty" ,  60 =>    "sixty" , 70 =>  "seventy" ,
       80 =>    "eighty" ,  90 =>    "ninety"
  }

  Unit = [ "", "thousand", "million", "billion" ]
  UpperBound = 10**12

  def initialize n
    fail ArgumentError if n < 0 || n >= UpperBound
    @number = n
  end

  def in_english
    return NumberLookup[@number] if non_compound_utterance?
    comma_groups.each_with_object("") do |(digs,unit),words|
      words << spell_out_a_unit(digs, unit)
    end.rstrip
  end

  def spell_out_a_unit digits, unit
    value = digits.join.to_i
    return "#{NumberLookup[value]} #{unit} " if non_compound_utterance? value

    hundreds = value / 100
    tens = ((value-hundreds*100)/10)*10
    ones = value%10
    sep = ""
    sep = "-" if ones > 0

    text = ""
    text << "#{NumberLookup[hundreds]} hundred " if hundreds > 0
    text << "#{NumberLookup[tens]}#{sep}" if tens > 0
    text << "#{NumberLookup[ones]}" if ones > 0
    text << " #{unit} "
  end

  private
    def non_compound_utterance? value=@number
      value < 20 || [20, 30, 40, 50, 60, 70, 80, 90].include?(value)
    end

    def comma_groups
      # Given a number, break it down into a list of tuples
      # [up-to-3-digits, unit-description]
      # Ex:
      #   1234 -> [[["1"], "thousand"], [["2", "3", "4"], ""]]
      digits = @number.to_s.chars.reverse.each_slice(3).map { |i| i.reverse }
      digits.zip(Unit).reverse.find_all { |digs,u| digs.join.to_i > 0 }
    end
end
