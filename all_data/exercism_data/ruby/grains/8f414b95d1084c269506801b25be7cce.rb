class Grains
  def square(number)
    return 1 if number == 1
    return 2 ** (number - 1)
  end

  def total
    grand_total = 1
    (2..64).each do | i |
      grand_total += square(i)
    end
    return grand_total
  end
end
