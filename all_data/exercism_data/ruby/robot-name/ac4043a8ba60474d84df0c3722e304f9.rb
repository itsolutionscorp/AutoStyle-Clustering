class Robot
  def initialize
    @name = [('AA'..'ZZ').to_a.sample, (100..999).to_a.sample].join
  end
  def reset
    initialize
  end
  def name
    @name
  end
end
