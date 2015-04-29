class Robot
  attr_accessor :name

  def initialize
    @name = generate_name
  end

  def generate_name
    name = []
    2.times{name  << (65 + rand(25)).chr}
    3.times{name << (rand*10).to_i}
    name.join("")
  end

  def reset
    @name = generate_name
  end
end
