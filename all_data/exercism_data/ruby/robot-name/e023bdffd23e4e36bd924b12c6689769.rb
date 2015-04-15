class Robot
  attr_accessor :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  def generate_name
    prefix = (0...2).map { (65 + rand(26)).chr }.join << rand(100..999).to_s
  end
end
