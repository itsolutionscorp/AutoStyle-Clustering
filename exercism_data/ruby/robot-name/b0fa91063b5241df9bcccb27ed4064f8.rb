class Robot
  LETTERS = ('A'..'Z').to_a
  NUMBERS = (1..9).to_a

  def initialize
    @name = generate_name
  end

  def name
    @name
  end

  def reset
    @name = generate_name
  end

  def generate_name
    "#{LETTERS.sample}#{LETTERS.sample}#{NUMBERS.sample}#{NUMBERS.sample}#{NUMBERS.sample}"
  end
end
