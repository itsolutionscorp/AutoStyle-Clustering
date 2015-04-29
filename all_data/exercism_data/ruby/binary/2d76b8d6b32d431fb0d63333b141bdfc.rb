class Binary

  def initialize(s)
    @s = s.tr('^01', '') == s ? s : "0"
  end

  def to_decimal
    @s.chars.inject(0) { |n, c| n += c.to_i + n }
  end

end
