class Grains

  def square(i)
    2**(i-1)
  end

  def total
    (1..64).inject(0) do |grains, i|
      grains + square(i)
    end
  end

end
