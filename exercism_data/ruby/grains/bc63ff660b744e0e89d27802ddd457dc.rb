class Grains
  GRAINS = (0...64).map { |n| 2 ** n }.freeze
  TOTAL = GRAINS.inject(&:+).freeze

  def square(n)
    GRAINS[n-1]
  end

  def total
    TOTAL
  end
end
