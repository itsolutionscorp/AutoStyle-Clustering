class Robot
  attr_reader :name

  CHARS = ('A'..'Z').to_a + (0..9).to_a.map(&:to_s)

  def initialize
    set_name
  end

  def reset
    set_name
  end

  private

  def set_name
    @name = self.class.generate_name
  end

  def self.generate_name
    ('A'..'Z').to_a.sample(2).join + rand.to_s[2...5]
  end
end
