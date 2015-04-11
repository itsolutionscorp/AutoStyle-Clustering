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
      translate_unit("billion", 12, 10),
      translate_unit("million", 9, 7),
      translate_unit("thousand", 6, 4),
      translate_unit("hundred", 3, 3),
      tens_and_ones,
    ].compact.join(" ")
  end

  def tens_and_ones
    parts = [
      TENS[subnumber(2, 2)],
      translate_subnumber(1, 1),
    ].compact

    parts.any? ? parts.join("-") : nil
  end

  def translate_unit(unit, from_position, to_position)
    value = translate_subnumber(from_position, to_position)
    value && "#{value} #{unit}"
  end

  def translate_subnumber(from_position, to_position)
    subnum = subnumber(from_position, to_position)
    subnum.nonzero? && recurse(subnum)
  end

  def subnumber(from_position, to_position)
    number % 10**from_position / 10**(to_position - 1)
  end

  def recurse(number)
    self.class.new(number).in_english
  end

  attr_reader :number
end
