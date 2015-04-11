class Robot

  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    letter + letter + three_digit_number
  end

  def letter
    rand(65..90).chr
  end

  def three_digit_number
    rand(100..1000).to_s
  end

end
