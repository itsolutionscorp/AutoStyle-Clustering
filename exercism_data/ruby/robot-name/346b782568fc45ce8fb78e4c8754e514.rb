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
    name = random_name while @names.include?(name = random_name)
    @names << name
    name
  end

  private

  def random_name
    random_letters(2) + random_digits(3)
  end

  def random_letters(n)
    (0...n).map { ("A".."Z").to_a.sample }.join
  end

  def random_digits(n)
    (0...n).map { rand(10) }.join
  end
end