class Robot
  attr_accessor :name

  def initialize
    @name = generate_name
  end

  def reset
    initialize
  end

  private

  def generate_name
    2.times.map { UPPER.sample }.join +
      3.times.map { DIGIT.sample }.join
  end

  UPPER = ('A'..'Z').to_a
  DIGIT = (0..9).to_a
end
