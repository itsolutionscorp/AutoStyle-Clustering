require "set"

class Robot
  attr_reader :name

  def initialize(name_generator = RobotNameGenerator.new,
                 used_names = Set.new)
    @name_generator = name_generator
    @used_names = used_names
    reset
  end

  def reset

    begin
      name = @name_generator.generate
    end while @used_names.include? name

    @used_names.add(name)
    @name = name
  end
end

class RobotNameGenerator

  def generate
    LETTERS.sample(2).join + NUMBERS.sample(3).join
  end

  private

  NUMBERS = 0.upto(9).to_a
  LETTERS = ("A".."Z").to_a

end
