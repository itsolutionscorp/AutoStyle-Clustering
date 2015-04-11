class Robot

  def initialize
    @name = random_name
  end

  attr_reader :name

  def reset
    @name = random_name
  end

  private

  def random_name
    (1..2).map { (65 + rand(26)).chr }.join + (100..999).to_a.sample.to_s
  end
end
