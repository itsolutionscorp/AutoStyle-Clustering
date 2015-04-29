class Grains

  def square(number)
    if number < 3
      number
    else
      square(number - 1) * 2
    end
  end

  def total
    (1..64).inject { |total, num| total += square(num) }
  end

end
