class Robot
  attr_reader :name

  CHARS = ('A'..'Z').to_a + (0..9).to_a.map(&:to_s)

  def initialize
    @name = self.class.generate_name
  end

  def reset
    @name = self.class.generate_name
  end

  private

  def self.generate_name
    letters = (0...2).map{ ('A'..'Z').to_a.sample }.join
    number = rand.to_s[2...5]
    letters + number
  end
end
