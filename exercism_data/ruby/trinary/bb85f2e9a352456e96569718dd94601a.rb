class Trinary
  def initialize(value)
    @str = value
  end

  def to_i
    @v ||= str.reverse.chars.map.with_index { |d, i| d.to_i * 3**i }.inject(:+)
  end

  alias :to_decimal :to_i

  private

  attr_reader :str

end
