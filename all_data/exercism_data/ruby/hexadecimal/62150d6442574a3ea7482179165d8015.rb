class Hexadecimal
  def initialize hex
    @hex = hex =~ /[^0-9a-f]/i ? '0' : hex
  end

  def to_decimal
    @hex.chars.inject(0) { |sum, n| sum * 16 + to_num(n) }
  end

  private

  def to_num x
    case x.downcase
    when 'a' then 10
    when 'b' then 11
    when 'c' then 12
    when 'd' then 13
    when 'e' then 14
    when 'f' then 15
    else x.to_i
    end
  end
end
