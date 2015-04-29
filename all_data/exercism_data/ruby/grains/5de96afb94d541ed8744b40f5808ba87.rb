class Grains
  def square n
    2 ** (n - 1)
  end

  def total
    (1..64).to_a.map{|e| square e}.reduce(:+)
  end
end
