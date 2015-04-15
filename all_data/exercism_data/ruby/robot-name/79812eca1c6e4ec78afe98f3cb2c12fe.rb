class Robot
  attr_accessor :name

  LETTERS = [*("A".."Z"), *("a".."z")].flatten
  NUMBERS = [*(0..9)]

  def initialize
    @name = create_name
  end

  def reset
    initialize
  end

  private

  def create_name
    [LETTERS.sample(2), NUMBERS.sample(3)].join("")
  end
end
