class Robot

  attr_reader :name

  def initialize
    generate_name
  end

  def reset
    generate_name
  end

  def generate_name
    alpha_range = ('A'..'z').to_a.sample(2).join("")
    numerical_range = (0..9).to_a.sample(3).join("")
    @name = alpha_range + numerical_range
  end

end
