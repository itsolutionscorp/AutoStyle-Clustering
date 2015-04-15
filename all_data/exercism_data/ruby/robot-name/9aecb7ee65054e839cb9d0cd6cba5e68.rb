class Robot
  attr_accessor :name

  ALPHABET = ('a'..'z').to_a + ('A'..'Z').to_a

  def initialize
    reset
  end

  def reset
    @name = Robot.generate_name
  end

  private

  def self.generate_name
    ALPHABET.sample + ALPHABET.sample + rand(0..9).to_s.rjust(3, '0')
  end
end
