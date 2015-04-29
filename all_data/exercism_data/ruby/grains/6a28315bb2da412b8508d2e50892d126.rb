class Grains

  def square(number)
    grains = 1
    2.upto(number){ grains += grains }
    grains
  end

  def total
    total = 0
    1.upto(64){ |value| total += square(value) }
    total
  end

end
