class Grains

  def square(num_of_square)
    2**(num_of_square-1)
  end

  def total
    (1..64).to_a.inject(0) do |grains, num_square|
      grains + square(num_square)
    end
  end

end
