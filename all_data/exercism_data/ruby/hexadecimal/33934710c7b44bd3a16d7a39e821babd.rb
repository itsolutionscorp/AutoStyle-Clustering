class Hexadecimal
  BASE = 16

  def initialize(digits)
    @digits = digits.upcase
  end

  def to_decimal
    return 0 unless valid?

    @digits.chars.map.with_index do |digit, idx|
      convert(digit) * (BASE ** (@digits.length - idx - 1))
    end.inject(0, :+)
  end


  private
  def valid?
    @digits.length == @digits.gsub(/[^0-9A-F]/, "").length
  end

  def convert(digit)
    case digit.ord
    when "0".ord .. "9".ord
      digit.ord - "0".ord
    when "A".ord .. "F".ord
      digit.ord - "A".ord + 10
    end
  end
end
