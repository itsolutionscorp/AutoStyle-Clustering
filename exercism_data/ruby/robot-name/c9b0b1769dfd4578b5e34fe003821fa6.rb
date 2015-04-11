class Robot
  attr_reader :name
  def initialize
    reset
  end

  def reset
    letters = ('A'..'Z').to_a.shuffle
    numbers = ('1'..'9').to_a.shuffle
    @name = letters[0..1].join + numbers[0..2].join
  end
end
