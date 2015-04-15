class Grains

  def square(number)
    if number < 3
      number
    else
      square(number - 1) * 2
    end
  end

  def total
    (1..64).to_a.collect { |i| Grains.new.square(i) }.inject(&:+)
  end

end
