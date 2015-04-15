class Anynary
  def initialize(base, str)
    @base = base
    @str = str
  end

  def to_decimal
    @str.chars.reduce(0) do |acc, x|
      return 0 unless i = @base.index(x)
      acc * @base.length + i
    end
  end
end

class Hexadecimal < Anynary
  def initialize(str)
    super(('0'..'9').to_a + ('A'..'F').to_a, str.upcase)
  end
end
