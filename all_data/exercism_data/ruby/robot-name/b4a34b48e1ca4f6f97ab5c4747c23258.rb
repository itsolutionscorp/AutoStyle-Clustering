class Robot

  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = generate_random_name
  end

  private

  # => 'RX837'
  def generate_random_name
    "#{generate_random_letter}#{generate_random_letter}#{generate_random_number}"
  end

  # Returns a random letter between 'A' and 'Z' inclusive.
  def generate_random_letter
    rand('A'.ord..'Z'.ord).chr
  end

  # Returns a random number between 100 and 999 inclusive.
  def generate_random_number
    rand(100..999)
  end

end
