class Trinary
  def self.to_decimal(string)
    case string
    when /[^012]/ then 0
    when '0'..'2' then '012'.index(string)
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
