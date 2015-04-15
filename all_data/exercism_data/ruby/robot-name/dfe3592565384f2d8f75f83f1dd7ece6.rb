class Robot
  attr_reader :name
  def initialize
    setup
  end
  def reset
    setup
  end

  private
  def setup
    @name = (0...2).map { (65 + rand(26)).chr }.join + (0...3).map { rand(10) }.join
  end
end
