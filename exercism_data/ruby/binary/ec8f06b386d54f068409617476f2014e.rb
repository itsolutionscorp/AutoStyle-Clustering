class Binary

  def initialize(s)
    if s.tr('^01', '') != s then
      @s = "0"
    else
      @s = s
    end
  end

  def to_decimal
    n = 0
    @s.chars.reverse.each_with_index { |c, i| n += c.to_i * (2 ** i) }
    n
  end

end
