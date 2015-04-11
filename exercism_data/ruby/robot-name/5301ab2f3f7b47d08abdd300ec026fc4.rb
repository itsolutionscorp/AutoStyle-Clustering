class Robot
  attr_reader :name

  def initialize
    reset
  end

  @@names = {}
  def reset
    @@names[name] = false if name
    @name = generate_name
    return @@names[name] = true unless @@names[name]
    reset
  end

  private

  def generate_name
    ([*'A'..'Z'].sample(2) + [*'0'..'9'].sample(3)).join
  end
end
