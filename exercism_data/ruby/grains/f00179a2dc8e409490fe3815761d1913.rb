class Grains

  def square(place)
    total = 2**(place - 1)
  end

  def total
    value = 0
    (1..64).each { |place| value += square(place)  }
    value
  end
end
