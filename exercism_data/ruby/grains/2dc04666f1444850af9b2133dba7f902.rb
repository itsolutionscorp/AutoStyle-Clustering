class Grains
  def square(number)
    2**(number-1)
  end

  def total
    total = 0

    1.upto(64) do |number|
      total += square(number)
    end

    total
  end
end
