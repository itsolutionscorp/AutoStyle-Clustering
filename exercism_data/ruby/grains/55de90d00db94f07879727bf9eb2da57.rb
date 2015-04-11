class Grains

  def number_of_grains(square)
    2 ** (square - 1)
  end
  alias :square :number_of_grains

  def total
    1.upto(64).reduce(0) { |total, next_num|
      total + number_of_grains(next_num)
    }
  end
end
