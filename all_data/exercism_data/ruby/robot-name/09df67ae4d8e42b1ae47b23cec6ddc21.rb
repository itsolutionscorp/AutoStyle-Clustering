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
    ((1..2).map { ('A'..'Z').to_a[rand 26] } + (1..3).map { rand 10 }).join
  end
end
