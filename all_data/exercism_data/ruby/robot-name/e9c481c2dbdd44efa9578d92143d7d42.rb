require 'parallel'

class Robot
  attr_reader :name

  def initialize
    reset
  end

  @@names = {}
  def reset
    @@names[name] = false
    next while @@names[n = generate_name]
    @@names[n], @name = true, n
  end

  private

  def generate_name
    ([*'A'..'Z'].sample(2) + [*'0'..'9'].sample(3)).join
  end

end
