# Class Trinary, to convert trinary numbers to decimal
class Trinary
  def initialize(valstr)
    @trits = valstr =~ /[^0-2]/ ? [] : valstr.chars.map(&:to_i)
  end

  def to_decimal
    @trits.inject(0) { |a, e| a * 3 + e }
  end
end
