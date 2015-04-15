class Trinary
  def self.to_decimal(string)
    case string
    when '0', /[^012]/ then 0
    when '1' then 1
    when '2' then 2
    else
      string.reverse.each_char.with_index
        .reduce(0) { |a, (c, i)| a + to_decimal(c) * 3**i }
    end
  end

  def initialize(string)
    @string = String(string)
  end

  def to_decimal
    self.class.to_decimal(@string)
  end
end
