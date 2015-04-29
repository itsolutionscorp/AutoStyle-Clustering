class Robot
  attr_reader :name

  def initialize(name_generator = RobotNameGenerator.new)
    @name_generator = name_generator
    @name_history = RobotNameHistory.new
    reset
  end

  def reset
    begin
      name = @name_generator.generate
    end while @name_history.contains? name

    @name_history.used_names.push(name)
    @name = name
  end
end

class RobotNameHistory

  attr_reader :used_names

  def initialize
    @used_names = []
  end

  def contains?(name)
    not @used_names.index(name).nil?
  end
end

class RobotNameGenerator

  def generate
    name_part_with(2, lambda { rand_letter }) +
      name_part_with(3, lambda { rand_number_char })
  end

  private

  LETTERS = ("A".."Z").to_a

  def name_part_with(count, type)
    count.times.append(type)
  end

  def rand_letter
    LETTERS[rand(LETTERS.length)]
  end

  def rand_number_char
    rand(10).to_s
  end
end

class Enumerator
  def append(block)
    self.reduce("") { |result| result += block.call() }
  end
end


class RobotTest < MiniTest::Unit::TestCase

  def test_name_letters_should_not_be_duplicated
    robot = Robot.new
    chars = robot.name.chars
    assert chars.uniq == chars, "expected no duplicate characters in " + robot.name
  end

  def test_name_should_not_be_duplicated
    mock_name_generator = MockNameGenerator.new(%w(AA000 AA000 AA000 AA000 BB000))

    robot = Robot.new(mock_name_generator)
    assert robot.name == "AA000"

    robot.reset

    assert robot.name == "BB000", "expected BB000 but was " + robot.name
  end

  class MockNameGenerator

    attr_accessor :names

    def initialize(names = [])
      @names = names
      @requested_names = -1
    end

    def generate
      @requested_names += 1
      @names[@requested_names]
    end
  end
end
