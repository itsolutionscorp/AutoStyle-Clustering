class Robot
  ALBHABET = ("A".."Z").to_a
  DIGITS = (0..9).to_a

  def initialize
    @name = generate_name
  end

  def reset
    @name = nil
  end

  def name
    @name ||= generate_name
  end

  private

  def generate_name
    "#{ALBHABET.sample(2).join}#{DIGITS.sample(3).join}"
  end
end
