class Grains
  def square(number)
    2 ** (number - 1)
  end

  def total
    (1..64).to_a.map do |number|
      square(number)
    end.inject(:+)
  end
end
