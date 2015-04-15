class Robot
  attr_accessor :name

  def initialize
    generate_name
  end

  def reset
    generate_name
  end

  private

  def generate_name
    @name = "#{random_char}#{random_char}#{random_number}"
  end

  # Random sequence of 3 letters
  def random_char
    (65 + rand(26)).chr
  end

  # Random sequence of 3 numbers
  def random_number
    rand(100..999)
  end
end
