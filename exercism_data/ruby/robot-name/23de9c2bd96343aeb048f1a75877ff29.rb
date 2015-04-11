class Robot
  attr_reader :name

  def initialize
    @name = RobotNameGenerator.new_name
  end

  def reset
    initialize
  end
end

class RobotNameGenerator
  @names = []

  def self.names
    @names
  end

  def self.new_name
    new.new_name
  end

  def new_name
    name = random_name
    if RobotNameGenerator.names.include?(name)
      self.new_name
    else
      RobotNameGenerator.names << name
      name
    end
  end

  private

  def random_name
    (0..1).map { ("A".."Z").to_a.sample }.join + (0..2).map { rand(10) }.join
  end
end
