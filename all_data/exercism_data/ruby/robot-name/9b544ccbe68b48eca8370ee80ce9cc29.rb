class Robot
  def initialize
    reset
  end

  def name
    @name
  end

  def reset
    letters = ('A'..'Z').to_a.sample(2).join("")
    numbers = (1..9).to_a.sample(3).join("")
    @name = letters + numbers
  end
end
