class Robot
  attr_reader :name

  def initialize(name_generator = RobotNameGenerator.new,
                 used_names = [])
    @name_generator = name_generator
    @used_names = used_names
    reset
  end

  def reset

    begin
      name = @name_generator.generate
    end while @used_names.include? name

    @used_names.push(name)
    @name = name
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
    count.times.map { type.call  } .join
  end

  def rand_letter
    LETTERS[rand(LETTERS.length)]
  end

  def rand_number_char
    rand(10).to_s
  end

end
