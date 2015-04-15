class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = generate_str
  end

  private

  def generate_str
    (0...2).map { (65 + rand(26)).chr }.join << (0...3).map{ rand(26).to_s }.join
  end

end
