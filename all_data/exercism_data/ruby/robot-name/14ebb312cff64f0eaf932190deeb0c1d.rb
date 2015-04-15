class Robot
  attr_reader :name

  def initialize
    init_name
  end

  def reset
    init_name
  end

  private

  def init_name
    @name = generate_name
  end

  def generate_name
    "#{two_random_capital_letters}#{three_random_digits}"
  end

  def two_random_capital_letters
    (1..2).map { (65 + rand(26)).chr }.join
  end

  def three_random_digits
    "%03d" % rand(1000)
  end

end
