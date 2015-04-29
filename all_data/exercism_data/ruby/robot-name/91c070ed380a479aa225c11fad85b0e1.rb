class Robot
  attr_accessor :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    alphabets = ('A'..'Z').to_a
    numbers = (0..9).to_a
    (alphabets.sample(2) + numbers.sample(3)).join
  end
end
