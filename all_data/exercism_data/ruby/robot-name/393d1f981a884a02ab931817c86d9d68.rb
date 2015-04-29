class Robot
  attr_accessor :name

  def initialize
    letter = "abcdefghijklmnopqrstuvwxyz"
    @name = letter[rand(26)] + letter[rand(26)] + rand(10).to_s + rand(10).to_s + rand(10).to_s
  end

  def reset
    letter = "abcdefghijklmnopqrstuvwxyz"
    self.name = letter[rand(26)] + letter[rand(26)] + rand(10).to_s + rand(10).to_s + rand(10).to_s

  end
end
