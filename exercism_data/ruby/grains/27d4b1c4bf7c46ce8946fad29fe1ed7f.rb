class Grains
  def square(id)
    id == 1 ? 1 : Grains.new.square(id - 1) * 2
  end

  def total
    (1..64).inject { |sum, num| sum += Grains.new.square(num) }
  end
end
