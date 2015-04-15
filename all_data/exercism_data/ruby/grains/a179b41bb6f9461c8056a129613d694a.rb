class Grains
  def square input
	 2 ** (input - 1)
  end

  def total
    square(65) - 1
  end
end
