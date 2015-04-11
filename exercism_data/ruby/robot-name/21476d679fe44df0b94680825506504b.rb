class Robot

  attr_accessor :name

  def initialize
    self.name = Robot.create_new_name
  end

  def reset
    self.name = Robot.create_new_name
  end

  private

  def self.create_new_name
    letter = possible_letters.sample(2).join
    digits = "%03d" % rand(1000)
    return letter + digits
  end

  def self.possible_letters
    ('a'..'z').to_a.concat(('A'..'Z').to_a)
  end
end
