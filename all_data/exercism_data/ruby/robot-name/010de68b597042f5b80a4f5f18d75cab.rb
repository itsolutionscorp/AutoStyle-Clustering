class Robot
  def initialize
    @name = nil
  end

  def name
    @name.nil? ? @name = 2.times.map { (65 + rand(26)).chr }.join + 3.times.map { rand(10).to_s }.join : @name
  end

  def reset
    @name = nil
  end
end
