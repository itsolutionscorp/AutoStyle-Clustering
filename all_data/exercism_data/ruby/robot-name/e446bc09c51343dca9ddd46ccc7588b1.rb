class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split(//).sample(2).join + rand(100..999).to_s
  end
  
end
