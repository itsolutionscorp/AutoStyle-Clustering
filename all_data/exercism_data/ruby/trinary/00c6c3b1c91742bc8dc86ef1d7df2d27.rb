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

class Trinary < Anynary
  def initialize(str)
    super(%w(0 1 2), str)
  end
end
