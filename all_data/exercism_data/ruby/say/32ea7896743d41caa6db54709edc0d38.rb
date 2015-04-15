class DigitGroup
  DIGITS = %w(zero one two three four five six seven eight nine)

  TENS = %w(zero ten twenty thirty forty fifty sixty seventy eighty ninety)

  TEENS = %w(ten eleven twelve thirteen fourteen fifteen sixteen
             seventeen eighteen nineteen)

  NAMES = %w(zero thousand million billion)

  def initialize(group_number, digits)
    @group_number = group_number
    @digits = digits
  end

  def in_english
    [hundreds, tens_and_ones, name].join(" ").strip
  end

  private
  def hundreds
    DIGITS[hundreds_digit] + " hundred" unless hundreds_nil_or_zero?
  end

  def tens_and_ones
    teens || tens || ones
  end

  def teens
    TEENS[ones_digit] if tens_digit == 1 && ones_digit 
  end

  def tens
    TENS[tens_digit] + (dash_ones || "") unless tens_nil_or_zero?
  end

  def ones
    DIGITS[ones_digit] unless ones_nil_or_zero?
  end

  def dash_ones
    "-" << ones unless ones_nil_or_zero?
  end

  def name
    NAMES[@group_number] if should_say_name?
  end

  def hundreds_digit
    @digits[2]
  end

  def tens_digit
    @digits[1]
  end

  def ones_digit
    @digits[0]
  end

  def hundreds_nil_or_zero?
    nil_or_zero? 2
  end

  def tens_nil_or_zero?
    nil_or_zero? 1
  end

  def ones_nil_or_zero?
    nil_or_zero? 0
  end

  def nil_or_zero?(digit)
    @digits[digit].nil? || @digits[digit].zero?
  end

  def all_digits_zero?
    3.times { |digit| return false unless nil_or_zero?(digit) }
    true
  end

  def should_say_name?
    !@group_number.zero? && !all_digits_zero?
  end
end

class Say
  def initialize(n)
    raise ArgumentError, "No negatives, please" if n < 0
    raise ArgumentError, "I can't count to a trillion" if n >= 10**12

    @n = n
  end

  def in_english
    return "zero" if @n.zero?
    groups.each_with_object([]) { |group, group_set|
        g = group.in_english
        group_set.unshift g unless g.empty?
      }.join(" ")
  end

  private
  def groups
    (log_1000 + 1).times.map do |group_number|
      DigitGroup.new(group_number, digits_for_group(group_number))
    end
  end

  def digits_for_group(number)
    digits((@n / 10 ** (3 * number)) % 1000)
  end

  def digits(number)
    ds = []
    while number > 0
      ds.push number % 10
      number = number / 10
    end
    ds
  end

  def log_1000
    return 0 if @n.zero?
    Math.log10(@n).to_i / 3
  end
end
