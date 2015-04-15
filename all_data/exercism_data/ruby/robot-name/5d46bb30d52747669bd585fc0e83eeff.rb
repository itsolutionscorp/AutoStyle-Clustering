class Robot
  attr_accessor :name
  def initialize
    @name = (0...2).map { (65 + rand(26)).chr }.join << 3.times.map{rand(10)}.join
  end
  def reset
    initialize
  end
end
