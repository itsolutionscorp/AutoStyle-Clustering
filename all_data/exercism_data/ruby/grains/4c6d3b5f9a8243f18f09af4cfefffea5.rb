class Grains
  def square nr
    1 << nr - 1
  end

  def total
    square(64) * 2 - 1
  end
end
