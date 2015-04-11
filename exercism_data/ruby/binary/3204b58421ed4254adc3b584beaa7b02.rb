class Binary
  def initialize str
    @str = str
  end

  def to_decimal
    return 0 if @str.match(/[^01]/)
    @str.reverse.chars.map.with_index do |s, i|
      (2 ** i) * s.to_i
    end.inject(0, :+)
  end
end
