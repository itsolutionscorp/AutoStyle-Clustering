class Grains
  def square(x)
    2**(x - 1)
  end

  def total
    (1..64).inject(0) do |result, element|
      result + square(element)
    end
  end
end
