class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = ('A'..'Z').to_a.sample(2).join + rand(100..999).to_s
  end
  
end
