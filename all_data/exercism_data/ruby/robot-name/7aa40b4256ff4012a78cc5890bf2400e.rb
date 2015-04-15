class Robot
  attr_reader :name
  
  def initialize
    reset
  end

  def reset
    @name = "#{Robot.rand_char}#{Robot.rand_char}#{rand 9}#{rand 9}#{rand 9}"
  end

  def self.rand_char
    rand((65..90)).chr
  end
end
