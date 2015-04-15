module RobotNameGenerator
  class << self
    def generate
      "#{string}#{number}"
    end

    private

    def string
      "#{random_char}#{random_char}"
    end

    def random_char
      Random.rand(('A'.ord..'Z'.ord)).chr
    end

    def number
      Random.rand(999).to_s.rjust(3, '0')
    end
  end
end

class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = RobotNameGenerator.generate
  end
end
