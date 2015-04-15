class Trinary
  def initialize num
    @num = num
  end

  def to_decimal
    return 0 if @num !~ /\b[012]+\b/
    @num.reverse.chars.map.with_index {
      |t, index| t.to_i * 3 ** index }.inject(:+)
  end
end
