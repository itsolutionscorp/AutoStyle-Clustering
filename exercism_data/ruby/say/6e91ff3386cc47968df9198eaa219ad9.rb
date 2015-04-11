class Say
  MIN = 0
  MAX = 999_999_999_999

  SIMPLE_NUMBERS = %w[
    zero one two three four five six seven eight nine
    ten eleven twelve thirteen fourteen fifteen sixteen seventeen eighteen nineteen
  ]
  TENS = [nil, nil] + %w[twenty thirty forty fifty sixty seventy eighty ninety]

  def initialize(number)
    @number = number
    ensure_within_bounds
  end

  def in_english
    simple_translation || complex_translation
  end

  private

  def ensure_within_bounds
    raise ArgumentError, "Must be at least #{MIN}" if number < MIN
    raise ArgumentError, "Must not exceed #{MAX}" if number > MAX
  end

  def simple_translation
    SIMPLE_NUMBERS[number]
  end

  def complex_translation
    [
      unit_translation(12, 9, "billion"),
      unit_translation( 9, 6, "million"),
      unit_translation( 6, 3, "thousand"),
      unit_translation( 3, 2, "hundred"),
      tens_and_ones,
    ].compact.join(" ")
  end

  def unit_translation(from_position, to_position, unit = nil)
    digits = subnumber(from_position, to_position)
    digits.nonzero? && [recurse(digits), unit].compact.join(" ")
  end

  def tens_and_ones
    parts = [
      TENS[subnumber(2, 1)],
      unit_translation(1, 0),
    ].compact

    parts.any? ? parts.join("-") : nil
  end

  def recurse(number)
    self.class.new(number).in_english
  end

  def subnumber(from, unto)
    number % 10**from / 10**unto
  end

  attr_reader :number
end
