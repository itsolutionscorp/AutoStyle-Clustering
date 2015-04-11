class Trinary

  attr_reader :number

  def initialize number
    @number = number
  end

  def to_decimal
    number.to_i 3
  end

end
