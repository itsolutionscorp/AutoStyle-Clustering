class Grains
  def square(id)
    id == 1 ? 1 : square(id - 1) * 2
  end

  def total
    (1..64).inject { |sum, num| sum += square(num) }
  end
end
