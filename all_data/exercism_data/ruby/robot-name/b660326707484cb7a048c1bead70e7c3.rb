class Robot

  @@taken_robot_names = []

  attr_accessor :name

  def initialize
    @name = generate_name
    loop { @name = generate_name } until !is_taken?(@name)
    @@taken_robot_names << @name
  end

  def reset
    @name = generate_name
  end

  private

  ALPHABET = ("A".."Z").to_a
  NUMBERS = (100..999).to_a

  def generate_name
    ALPHABET.sample(2).join + NUMBERS.sample.to_s
  end

  def is_taken?(name)
    @@taken_robot_names.detect { |name| name == @name }
  end
end
