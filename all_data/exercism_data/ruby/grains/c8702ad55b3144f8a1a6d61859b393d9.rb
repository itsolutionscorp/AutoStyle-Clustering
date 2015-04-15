class Grains
  def square(number)
    if number == 1
      return 1
    else
      return ((2**(number-1)))
    end
  end
  def total
    return ((2**64) - 1)
  end
end
