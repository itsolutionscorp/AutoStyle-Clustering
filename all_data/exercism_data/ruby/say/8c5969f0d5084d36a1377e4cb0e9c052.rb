class Say
  DIGITS = %w[zero one two three four five six seven eight nine]
  TEENS  = %w[eleven twelve thirteen fourteen fifteen sixteen seventeen eighteen nineteen]
  TENS   = %w[twenty thirty forty fifty sixty seventy eighty ninety]
  POWERS = {'billion' => 9, 'million' => 6, 'thousand' => 3}

  attr_reader :num

  def initialize(num)
    raise ArgumentError unless num >= 0 && num < 1000000000000
    @num = num
  end

  def in_english(n=num)
    case
    when n < 10
      DIGITS[n]
    when n < 20
      TEENS[n-11]
    when n < 100
      sub_100(n)
    when n < 1000
      sub_1000(n)
    else
      build_words(n)
    end
  end

  private

  def sub_100(n)
    tens, digits = n.divmod(10)
    return tens_str(tens) if digits == 0
    [tens_str(tens), digits_str(digits)].compact.join(' ')
  end

  def sub_1000(n)
    hundreds, tens = n.divmod(100)
    [hundreds_str(hundreds), sub_100(tens)].compact.join(' and ')
  end

  def build_words(n)
    power, remainder = n.divmod(10**largest_power(n))
    [power_str(power, n), extra_and(remainder), remainder_str(remainder)].compact.join(' ')
  end

  def digits_str(digits)
    return nil if digits == 0
    in_english(digits)
  end

  def tens_str(tens)
    return nil if tens == 0
    TENS[tens-2]
  end

  def hundreds_str(hundreds)
    "#{DIGITS[hundreds]} hundred"
  end

  def power_str(power, n)
    [in_english(power), POWERS.key(largest_power(n))].join(' ')
  end

  def largest_power(n)
    POWERS.values.detect { |exponent| n/(10**exponent) > 0 }
  end

  def remainder_str(n)
    return nil if n == 0
    in_english(n)
  end

  def extra_and(remainder)
    (num > 1000 && remainder < 100 && remainder > 0) ? 'and' : nil
  end
end
