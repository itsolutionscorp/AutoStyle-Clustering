class Grains
  def square(number)
    number_of_grains = 2 ** (number-1)
  end

  def total
    (1..64).inject(0) { |acc, number| square(number) + acc}
  end
end
