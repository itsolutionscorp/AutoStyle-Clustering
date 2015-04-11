class Robot

  attr_reader :name

  def initialize
    @name = [ LETTERS.sample(2) + NUMBERS.sample(3) ].join
  end

  def reset
    initialize
  end

  private
  LETTERS = ('A'...'Z').to_a + ('a'...'z').to_a
  NUMBERS = (1...9).to_a

end
