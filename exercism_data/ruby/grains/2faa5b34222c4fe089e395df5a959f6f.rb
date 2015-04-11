class Grains
  def square(index)
    raise ArgumentError unless (1..64).cover?(index)

    2 ** (index - 1)
  end

  def total
    @total ||= (0...64).map { |n| 2**n }.reduce(:+)
  end
end
