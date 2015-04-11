class Robot
  attr_reader :name

  def initialize(name_generator = RobotNameGenerator.new)
    @name_generator = name_generator
    assign_factory_settings
  end

  def reset
    assign_factory_settings
  end

  private

  def assign_factory_settings
    @name = @name_generator.new_name
  end
end

class RobotNameGenerator
  attr_reader :names

  def initialize
    @names = []
  end

  def new_name
    next while @names.include?(name = generate_random_name)
    @names << name
    name
  end

  private

  def generate_random_name
    ( random_letters(2) + random_digits(3) ).join
  end

  def random_letters(n)
    n.times.map { ("A".."Z").to_a.sample }
  end

  def random_digits(n)
    n.times.map { rand(10) } 
  end
end
