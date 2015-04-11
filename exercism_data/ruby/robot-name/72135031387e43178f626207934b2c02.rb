class Robot
  attr_accessor :name

  def initialize
    self.generate_random_name
  end

  def reset
    self.generate_random_name
  end

  def generate_random_name
    @name = ("A".."Z").to_a.sample(2).join + (0..9).to_a.sample(3).join
  end
end
