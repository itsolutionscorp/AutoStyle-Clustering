class Robot

  attr_accessor :name
  def initialize
    @name = random_name_generator
  end

  def reset
    self.name = random_name_generator
  end

  def random_name_generator
    "#{(0..1).map { (65 + rand(26)).chr }.join}#{(100 + rand(899)).to_s}"
  end
end
