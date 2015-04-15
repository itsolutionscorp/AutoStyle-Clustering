# class Trinary
class Trinary
  attr_accessor :str
  def initialize(str)
    @str = str
  end

  def validate(str)
    str.split(//).uniq.sort.join == '012' ? str : '0'
  end

  def to_decimal
    i = -1
    str.reverse.each_char.inject(0) do |mem, var|
      i += 1
      mem + var.to_i * 3**i
    end
  end
end
