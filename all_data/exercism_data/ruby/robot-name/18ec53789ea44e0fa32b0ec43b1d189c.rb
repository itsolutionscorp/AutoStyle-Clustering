class Robot
  attr_accessor :name

  def initialize
    @name = generate_name
  end

  def generate_name
    (0...2).map { (65 + rand(26)).chr }.push(rand.to_s[2..4]).join
  end

  def reset
    self.name = generate_name
  end
end
