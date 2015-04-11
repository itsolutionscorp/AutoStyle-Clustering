class Trinary

  def initialize(str)
    @str = str
  end

  def to_decimal
    sum = 0
    @str.reverse.each_char.with_index { |letter, i| sum += letter.to_i * 3 ** i }
    sum
  end

end
