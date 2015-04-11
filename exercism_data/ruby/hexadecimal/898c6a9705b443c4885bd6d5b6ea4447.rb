class Hexadecimal
  @@dict = {}

  def initialize(s)
    @s = s.to_s
    ((0..9).map(&:to_s) + ('a'..'f').to_a).each.with_index { |c, i| @@dict[c] = i }
  end

  def to_decimal
    return 0 if @s !~ /^[a-f\d]+$/
    @s.reverse.each_char.with_index.map { |c, i| 16**i * @@dict[c] }.inject(:+)
  end
end
