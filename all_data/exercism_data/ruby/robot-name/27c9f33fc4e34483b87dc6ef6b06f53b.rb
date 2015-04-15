class Robot
  @chars  = [*'A'..'Z'].freeze
  @digits = [*'1'..'9'].freeze

  def self.generate_name
    "#{@chars.sample}#{@chars.sample}#{@digits.sample}#{@digits.sample}#{@digits.sample}"
  end

  attr_reader :name

  def initialize
    @name = self.class.generate_name
  end

  alias_method :reset, :initialize
  public :reset
end
