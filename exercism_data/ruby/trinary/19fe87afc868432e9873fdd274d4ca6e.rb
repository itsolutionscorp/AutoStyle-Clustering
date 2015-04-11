class Trinary
  BASE = 3

  def initialize(trinumber)
    @trinumber = trinumber
  end

  def to_decimal
    trits.inject([0, 1]) do |(sum, factor), trit|
      sum += trit * factor
      [sum, factor * 3]
    end.first
  end

  private

  def trits
    @trinumber.reverse.chars.map(&:to_i)
  end
end
