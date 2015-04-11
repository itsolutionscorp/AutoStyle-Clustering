require 'pry'
class Grains
  def square(square_number)
      return 2 ** (square_number -1)
  end


  def total
    square(65)
  end
end
