class Grains

  def square(number)
    2 ** (number-1)
  end

  def total
    sub_totals = (1..64).map do |value|
      2 ** (value-1)
    end
    sub_totals.inject(:+)
  end
end
