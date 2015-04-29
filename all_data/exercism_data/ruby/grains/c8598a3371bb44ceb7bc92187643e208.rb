class Grains

  def square n
    0b10**(n-1)
  end

  def total
    @total ||= (1..64).reduce(0) do |sum, n|
      sum += square n
    end
  end

end
