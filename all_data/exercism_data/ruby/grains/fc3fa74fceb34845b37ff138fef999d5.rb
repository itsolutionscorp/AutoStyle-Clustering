class Grains
  def square(number)
    i = 1
    grains = 1
    while i < number
      grains = grains * 2
      i+= 1
    end
    return grains
  end
  def total
    2**64 - 1
  end
end
