class Robot
  LETTERS = ('A'..'Z').to_a

  attr_reader :name

  def initialize
    @name = "%s%s%03d" % [LETTERS.sample, LETTERS.sample, rand(100)]
  end

  def reset
    initialize
  end
end
