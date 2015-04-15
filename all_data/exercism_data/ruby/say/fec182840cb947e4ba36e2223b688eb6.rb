class Say
  ZERO_TO_TWELVE = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine', 'ten', 'eleven', 'twelve']
  TWENTY_TO_NINETY = ['twenty', 'thirty', 'forty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety']
  BIG_NUMBERS = {' billion' => 1000000000, ' million' => 1000000, ' thousand' => 1000}

  def initialize number
    @number = number
  end


  def one_to_nineninenine num
    this_result = ''

    if num > 99
      hundreds = num.to_s[0].to_i
      this_result << ZERO_TO_TWELVE[hundreds]
      num -= hundreds * 100
      this_result << ' hundred'
      this_result << ' ' if num > 0
    end

    if num > 19
      tens = num.to_s[0].to_i
      this_result << TWENTY_TO_NINETY[tens - 2]
      num -= tens * 10
      this_result << '-' if num > 0
    end

    if num > 12
      this_result << ZERO_TO_TWELVE[num.to_s[1].to_i] + 'teen'
      num = 0
    end

    this_result << ZERO_TO_TWELVE[num] if num > 0
    return this_result
  end

  def in_english
    raise ArgumentError if @number < 0 || @number > 999999999999
    return 'zero' if @number == 0
    result = ''

    BIG_NUMBERS.each {|k, n|
      if @number >= n
        o = @number / n
        result << one_to_nineninenine(o) + k
        @number -= n * o
        result << ' ' if @number > 0
      end
    }

    result << one_to_nineninenine(@number)
    result
  end

end
