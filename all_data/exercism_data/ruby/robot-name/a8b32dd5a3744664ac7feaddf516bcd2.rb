class Robot
  attr_accessor :name

  def initialize
    self.reset
  end

  def generate_name
    (0..1).map { (65 + rand(26)).chr } + (0..2).map { rand(10) }
  end

  def reset
    self.name = generate_name.join
  end

end
