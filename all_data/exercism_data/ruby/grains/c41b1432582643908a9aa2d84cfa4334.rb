class Grains
  def square(number)
    grains[number-1]
  end

  def total
    grains.inject(:+)
  end

  private

  def grains
    @_grains ||= (1..64).map { |e| 2 ** (e - 1) }
  end
end
