class Binary
  def initialize str
    @str = str
  end

  def to_decimal
    return 0 unless @str.to_i.to_s == @str
    @str.reverse.chars.map.with_index do |s, i|
      (2 ** i) * s.to_i
    end.inject(0, :+)
  end
end
