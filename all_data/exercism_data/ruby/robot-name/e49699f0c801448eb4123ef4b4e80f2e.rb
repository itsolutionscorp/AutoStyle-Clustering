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

  def random_char
    ('A'..'Z').to_a.sample
  end

  def random_number
    rand(100..999)
  end
end
