class Grains
  attr_reader :number

  def square(number)
    @number = number
    2 ** (number - 1)
  end

  def total
    square(65) - 1
  end

end
