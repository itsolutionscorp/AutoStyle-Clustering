class Robot
  attr_reader :name

  def initialize(name_generator = RobotNameGenerator.new)
    @name = name_generator.new_name
  end

  def reset
    initialize
  end
end

class RobotNameGenerator
  attr_reader :names

  def initialize
    @names = []
  end

  def new_name
    next while @names.include?(name = random_name)
    @names << name
    name
  end

  private

  def random_name
    ( random_letters(2) + random_digits(3) ).join
  end

  def random_letters(n)
    n.times.map { ("A".."Z").to_a.sample }
  end

  def random_digits(n)
    n.times.map { rand(10) } 
  end
end
