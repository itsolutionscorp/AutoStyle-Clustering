class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = random_name
  end

  private

  NUMBERS = (100..999).freeze
  ALPHA = 'A'..'Z'.freeze

  def random_name
    random_alpha + random_alpha + rand(NUMBERS).to_s
  end

  def random_alpha
    ALPHA.to_a[rand(ALPHA.size)]
  end
end
