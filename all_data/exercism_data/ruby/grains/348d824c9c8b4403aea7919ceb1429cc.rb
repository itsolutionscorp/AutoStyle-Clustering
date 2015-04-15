class Grains

  def square(square_number)
    (1...square_number).inject(1) { |sum| sum *= 2 }
  end

  def total
    (1..64).inject { |sum, square_number| sum += square(square_number) }
  end

end
