class RandomName
  def to_s
    random_letter + random_letter + padded_number_below_1000
  end

  private

  def random_letter
    format '%c', Random.rand(26) + 'A'.ord
  end

  def padded_number_below_1000
    format '%03d', Random.rand(1000)
  end
end

class Robot
  def initialize
    reset
  end

  def reset
    @name = RandomName.new.to_s
  end

  attr_reader :name
end
