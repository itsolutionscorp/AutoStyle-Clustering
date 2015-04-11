class Grains

  def number_of_grains(square)
    1 << (square - 1)
  end
  alias :square :number_of_grains

  def total(max_square=64)
    1.upto(max_square).reduce { |total, next_square|
      total + number_of_grains(next_square)
    }
  end
end
