class Say
  ZERO_TO_TWELVE = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine', 'ten', 'eleven', 'twelve']
  TWENTY_TO_NINETY = ['twenty', 'thirty', 'forty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety']
  BIG_NUMBERS = {' billion' => 1_000_000_000, ' million' => 1_000_000, ' thousand' => 1_000}

  def initialize number
    @number = number
  end


  def one_to_nineninenine num
    this_result = ''

    if num > 99
      hundreds = num / 100
      num -= hundreds * 100
      this_result << ZERO_TO_TWELVE[hundreds] + ' hundred '
    end

    if num > 19
      tens = num / 10
      num -= tens * 10
      this_result << TWENTY_TO_NINETY[tens - 2]
      this_result << '-' if num > 0
    end

    this_result << ZERO_TO_TWELVE[num % 10] + 'teen' if num > 12
    this_result << ZERO_TO_TWELVE[num] if num > 0 && num < 13

    this_result
  end

  def in_english
    raise ArgumentError if @number < 0 || @number > 999_999_999_999
    return 'zero' if @number == 0
    result = ''

    BIG_NUMBERS.each {|k, n|
      if @number >= n
        o = @number / n
        result << one_to_nineninenine(o) + k + ' '
        @number -= n * o
      end
    }

    result = (result << one_to_nineninenine(@number)).strip
  end

end
