class Robot
  attr_accessor :name

  def self.generate_name
    res = ""
    res << (65 + rand(26)).chr << (65 + rand(26)).chr
    res << rand(10).to_s << rand(10).to_s << rand(10).to_s
  end

  def initialize
    @name = Robot.generate_name
  end

  def reset
    @name = Robot.generate_name
  end

end
