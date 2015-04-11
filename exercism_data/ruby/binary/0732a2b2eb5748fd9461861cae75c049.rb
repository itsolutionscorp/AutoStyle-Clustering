class Binary
  def initialize(str)
    @str = str
  end

  def to_decimal
    x = @str.length - 1
    n = 0
    @str.each_char do |b|
      break 0 if !%(0 1).include?(b)
      n += b.to_i * 2 ** x
      x -= 1
    end
    n
  end
end
