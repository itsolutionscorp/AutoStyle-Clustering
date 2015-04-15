class Grains
  def square(x)
    2**(x - 1)
  end

  def total
    [*(0..63)].inject(0) do |result, element|
      result + 2**element
    end
  end
end
