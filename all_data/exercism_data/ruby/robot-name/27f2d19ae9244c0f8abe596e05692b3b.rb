class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = name_part_with(2, rand_letter) +
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
