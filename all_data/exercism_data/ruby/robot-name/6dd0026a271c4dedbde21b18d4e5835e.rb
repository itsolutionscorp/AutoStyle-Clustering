class Robot
  attr_reader :name

  def initialize
    @name = [*"AA".."ZZ"].sample + [*"100".."999"].sample
  end

  def reset
    initialize
  end
end
