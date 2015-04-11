class Grains
  def square(count)
    return count == 1 ? 1 : square(count-1)*2
  end

  def total
    (1..64).reduce{ |sum,space| sum + square(space) }
  end
end
