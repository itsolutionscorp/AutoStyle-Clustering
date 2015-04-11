class Robot

  def initialize
    reset
  end

  def reset
    letters = ('A'..'Z').to_a.sample(2)
    numbers = ('0'..'9').to_a.sample(3)
    @name = (letters + numbers).join
  end

  attr_reader :name

end
