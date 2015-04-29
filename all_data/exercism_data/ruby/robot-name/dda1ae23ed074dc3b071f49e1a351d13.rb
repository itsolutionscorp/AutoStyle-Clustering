class Robot
  ALPHAS = ('a'..'z').to_a + ('A'..'Z').to_a
  NUMBERS = (0..9).to_a

  def self.generate_name
    [ ALPHAS.sample,
      ALPHAS.sample,
      NUMBERS.sample,
      NUMBERS.sample,
      NUMBERS.sample ].join('')
  end

  def self.generate_unique_name
    name = generate_name
    name = generate_name while registry.include?(name)
    name
  end

  def self.registry
    @registry ||= Set.new
  end

  def initialize
    reset
  end

  def reset
    self.class.registry.delete(@name)
    @name = Robot.generate_unique_name
  end

  attr_reader :name
end
