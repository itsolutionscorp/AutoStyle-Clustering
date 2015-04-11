class Grains

  def square(count)
    2**(count - 1)
  end

  def total
    total = 0

    (1..64).to_a.each do |i|
      total += square(i)
    end

    total
  end

end
