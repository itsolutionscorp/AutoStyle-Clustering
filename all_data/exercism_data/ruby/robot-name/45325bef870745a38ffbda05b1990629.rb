require 'set'

class Robot
  attr_reader :name

  def initialize(robot_names = RobotNames)
    @robot_names = robot_names
    @name = robot_names.generate
  end

  def reset
    @name = robot_names.generate
  end

  private

  attr_reader :robot_names
end

module RobotNames
  def self.generate
    name = Name.generate
    return generate if used.include?(name)
    used.add(name) and name
  end

  def self.used
    @@used ||= Set.new
  end

  class Name
    def initialize
      @value = "#{two_letters}#{three_digits}"
    end

    def self.generate
      new.to_s
    end

    def to_s
      value
    end

    private

    attr_reader :value

    def two_letters
      2.times.map { letters[rand(26)] }.join
    end

    def three_digits
      3.times.map { rand(10) }.join
    end

    def letters
      @letters ||= ('A'..'Z').to_a
    end
  end
end
