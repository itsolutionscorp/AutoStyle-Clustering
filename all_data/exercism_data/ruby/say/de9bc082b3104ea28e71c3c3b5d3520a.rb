class Say

  attr_reader :number

  SUFFIXES = ["", "thousand", "million", "billion"]

  def initialize(number)
    @number = number
  end

  def in_english
    raise ArgumentError if number < 0 || hundreds_array.size > SUFFIXES.size
    return @in_english unless @in_english.nil?
    return @in_english = "zero" if number == 0
    @in_english = ""
    hundreds_array.size.times do |i|
      current_hundred = SayUnder999.new(hundreds_array[i])
      if current_hundred.number != 0
        @in_english = "#{current_hundred.in_english} #{SUFFIXES[i]} #{@in_english}"
      end
    end
    @in_english.strip!
  end

  def hundreds_array
    @hundreds_array ||= Array.new.tap do |arr|
      result = [number, 0]
      while result.first != 0 do
        result = result.first.divmod 1000
        arr << result.last
      end
    end
  end
  
  class SayUnder999
    attr_reader :number

    UNDER_TWENTY = {
      1 => 'one',   2 => 'two',   3 => 'three',
      4 => 'four',  5 => 'five',  6 => 'six',
      7 => 'seven', 8 => 'eight', 9 => 'nine',
      10 => 'ten',        11 => 'eleven',   12 => 'twelve',   13 => 'thirteen', 
      14 => 'fourteen',   15 => 'fifteen',  16 => 'sixteen',  
      17 => 'seventeen',  18 => 'eighteen', 19 => 'nineteen'
    }
    TENS = {
      2 => 'twenty',   3 => 'thirty',
      4 => 'forty',    5 => 'fifty',  6 => 'sixty',
      7 => 'seventy',  8 => 'eighty', 9 => 'ninty'
    }

    def initialize(number)
      @number = number
    end

    def in_english
      return @in_english unless @in_english.nil?
      return @in_english = "" if number == 0
      @in_enlish = case number
      when 1..19 then UNDER_TWENTY[number]
      when 20..99 then 
        ones = number%10 != 0 ? "-#{UNDER_TWENTY[number%10]}" : ""
        "#{TENS[number/10]}#{ones}"
      when 100..999 then 
        "#{SayUnder999.new(number/100).in_english} hundred #{SayUnder999.new(number%100).in_english}".strip
      end
    end
  end

end
