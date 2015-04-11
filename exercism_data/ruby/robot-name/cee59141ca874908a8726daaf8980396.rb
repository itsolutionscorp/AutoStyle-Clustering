class Robot
  @@robot_names = []

  attr_reader :name

  def initialize
    assign_name
  end

  def reset
    assign_name
  end

  private

  LETTERS = ('A'..'z').to_a
  DIGITS = ('0'..'9').to_a

  def assign_name
    @name = generate_unique_name
    @@robot_names << @name
  end

  def generate_unique_name
    name = ''
    2.times { name << LETTERS.sample }
    3.times { name << DIGITS.sample }
    if @@robot_names.include?(name)
      generate_unique_name
    else
      return name
    end
  end

end
