class Grains
  def total()
    grains = 0
    for i in 1..64
      grains += square(i)
    end
    grains
  end

  def square(num)
    2**(num-1)
  end
end
