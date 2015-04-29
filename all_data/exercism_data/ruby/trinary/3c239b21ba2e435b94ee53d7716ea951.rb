class Trinary
  attr_reader :trinumber
  def initialize(trinumber)
    @trinumber = trinumber
  end

  def to_decimal
    decimals = trigits.map.with_index { |trigit, index| trigit * 3**index }
    decimals.inject(0, :+)
  end

  def trigits
    @trinumber.reverse.chars.map(&:to_i)
  end
end
