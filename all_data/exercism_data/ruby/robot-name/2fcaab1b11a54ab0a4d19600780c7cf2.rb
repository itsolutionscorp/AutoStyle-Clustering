class Robot
  attr_accessor :name

  def initialize
    reset
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    (('A'..'Z').to_a.sample(2) << rand(9) << rand(9) << rand(9)).join
  end
end
