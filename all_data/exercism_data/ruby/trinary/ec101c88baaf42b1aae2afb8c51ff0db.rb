class Trinary
  def initialize(s)
    @s = s
  end

  def to_decimal
    t = 0
    @s.chars.reverse.each.with_index do |c, i|
      t += c.to_i * 3**i
    end
    t
  end
end
