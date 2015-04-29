class Robot
  attr_reader :name

  def initialize
    @name = RobotNameGenerator.new_name
  end

  def reset
    initialize
  end
end

module RobotNameGenerator
  extend self

  @names = []

  def names
    @names
  end

  def new_name
    name = random_name
    if names.include?(name)
      new_name
    else
      names << name
      name
    end
  end

  def random_name
    (0..1).map { ("A".."Z").to_a.sample }.join + (0..2).map { rand(10) }.join
  end
end
