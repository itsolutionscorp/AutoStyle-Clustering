class Grains
  def square(count)
    return 1 if count == 1
    square(count - 1) * 2
  end

  def total
    (1..64).reduce do |sum, space|
      sum += square(space)
    end
  end
end
