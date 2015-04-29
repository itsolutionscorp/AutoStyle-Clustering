class Grains
  MAX_SQUARES = 64
  
  def square(count)
    2 ** (count - 1)
  end
  
  def total
    (1..MAX_SQUARES).inject(0) do |total, n|
      total += square(n)
    end
  end
end
