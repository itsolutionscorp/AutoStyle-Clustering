class Grains
  def square(size)
    2**(size-1)
  end

  def total
    (0..63).inject(0) do |sum, n|
      sum += 2**n
    end
  end
end
