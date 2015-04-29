class Grains

  def square(n)
    2**(n-1)
  end

  def total
    arrayse = (0..63).to_a
    arrayse.each { |n| arrayse[n] = square(n+1)}
    arrayse.reduce(:+)
  end
end
