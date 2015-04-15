class Grains
  def square(number)
    if number == 1
      1
    else
      square(number - 1) * 2
    end
  end

  def total
    (1..64).inject(0){|total, number| total + square(number)}
  end

end
