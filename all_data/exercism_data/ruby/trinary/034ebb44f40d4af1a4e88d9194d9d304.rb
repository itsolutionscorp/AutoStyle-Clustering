class Trinary
  BASE = 3

  def initialize(convertable_string)
    @convertable_string = convertable_string
  end

  def to_decimal
    return 0 unless valid?
    chars.reverse.map.with_index { |factor, index|
      factor.to_i * (BASE ** index)
    }.reduce(:+)
  end

  private

  def valid?
    @convertable_string =~ /^[0-2]+$/
  end

  def chars
    @convertable_string.chars.to_a
  end
end
