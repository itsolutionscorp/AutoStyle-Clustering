class Say
  attr_reader :number

  UNITS = %w(_ one two three four five six seven eight nine ten eleven twelve
             thirteen fourteen fifteen sixteen seventeen eighteen nineteen)
  TENS = %w(_ _ twenty thirty forty fifty sixty seventy eighty ninety)

  RANGES = {
    unit:     0..19,
    ten:      20..99,
    hundred:  100..999,
    thousand: 1_000..999_999,
    million:  1_000_000..999_999_999,
    billion:  1_000_000_000..999_999_999_999
  }

  def initialize(number)
    fail ArgumentError unless number.between?(0, 999_999_999_999)
    @number = number
  end

  def in_english
    return "zero" if number.zero?

    convert(number).join(" ")
  end

  private

  def convert(number)
    method, _ = RANGES.find { |_, range| range.cover?(number) }
    Array(send(method, number)).flatten.compact
  end

  RANGES.each do |name, range|
    next if private_method_defined?(name)

    define_method(name) do |number|
      quotient, modulus = number.divmod(range.begin)
      [convert(quotient), name.to_s, convert(modulus)]
    end
  end

  def ten(number)
    quotient, modulus = number.divmod(10)

    [TENS[quotient], unit(modulus)].compact.join("-")
  end

  def unit(number)
    return if number.zero?

    UNITS[number]
  end
end
