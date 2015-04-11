class Grains

  def square(number)
    2**(number-1)
  end

  def total
    (1..64).collect do |number|
      2**(number-1)
    end.inject(:+)
  end

end
