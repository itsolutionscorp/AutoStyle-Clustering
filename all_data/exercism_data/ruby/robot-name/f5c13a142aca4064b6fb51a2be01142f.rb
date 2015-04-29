class Robot
  def initialize
    @name = [('A'...'Z').to_a.sample(2), ('0'...'9').to_a.sample(3)].join
  end
  def reset
    initialize
  end
  def name
    @name
  end
end
