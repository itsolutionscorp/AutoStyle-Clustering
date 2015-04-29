class Robot
  attr_accessor :name

  def initialize
    @name = random_name
  end

  def reset
    initialize
  end

  private
  def random_name
    chars = ('A'..'Z').to_a
    digits = (0..9).to_a
    chars.sample(2).join + digits.sample(3).join
  end
end
