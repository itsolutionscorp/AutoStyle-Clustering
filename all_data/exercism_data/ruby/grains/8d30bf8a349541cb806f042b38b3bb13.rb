class Grains
  def square(count)
    return 2 ** (count-1)
  end

  def total
    (1..64).reduce{ |sum,space| sum + square(space) }
  end
end
