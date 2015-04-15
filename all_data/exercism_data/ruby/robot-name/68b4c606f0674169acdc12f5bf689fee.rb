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
    name_part_with(2, rand_letter) +
      name_part_with(3, rand_number_char)
  end

  private

  LETTERS = ("A".."Z").to_a

  def name_part_with(count, type)
    count.times.reduce("") { |name| name += type }
  end

  def rand_letter
    LETTERS[rand(LETTERS.length)]
  end

  def rand_number_char
    rand(10).to_s
  end
end
