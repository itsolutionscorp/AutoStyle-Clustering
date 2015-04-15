class Robot
  attr_reader :name
  def initialize
    @name = randomize
  end

  def randomize
    letters = ('A'..'Z').to_a.shuffle
    numbers = ('1'..'10').to_a.shuffle
    letters[0..1].join + numbers[0..2].join
  end

  def reset
    @name = randomize
  end
end
