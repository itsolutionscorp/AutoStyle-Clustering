class Trinary
  BASE = 3

  def initialize(trinumber)
    @trinumber = trinumber
  end

  def to_decimal
    decimals.inject(0, :+)
  end

  private

  attr_reader :trinumber

  def decimals
    trits.map.with_index { |trit, index| trit * BASE**index }
  end

  def trits
    trinumber.reverse.chars.map(&:to_i)
  end
end
