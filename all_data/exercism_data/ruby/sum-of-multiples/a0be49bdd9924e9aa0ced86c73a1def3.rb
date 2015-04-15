class SumOfMultiples
  DEFAULT_MULTIPLIERS = [3,5]

  def initialize(*multipliers)
    @multipliers = multipliers
  end

  def self.to(to_number)
    SumOfMultiples.new(*DEFAULT_MULTIPLIERS).to(to_number)
  end

  def to(to_number)
    (1...to_number).select { |num| is_multiple?(num) }.reduce(0,:+)
  end

  private
  # Check if the given number is a multiple of one or more of @multipliers.
  def is_multiple?(num)
    @multipliers.select { |multiplier| num % multiplier == 0 }.length > 0
  end
end
