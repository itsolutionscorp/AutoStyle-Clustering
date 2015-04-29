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
    @name = "#{generate_random_letter}#{generate_random_letter}#{generate_random_number}#{generate_random_number}#{generate_random_number}"
  end

  def generate_random_letter
    ('A'..'Z').to_a.sample
  end

  def generate_random_number
    (1..9).to_a.sample
  end
end
