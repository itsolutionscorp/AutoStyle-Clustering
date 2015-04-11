class Robot
  attr_accessor :name

  def initialize
    reset
  end

  def reset
    @name = "#{ prefix }#{ number }"
  end

  private

  def prefix
    ('A'..'Z').to_a.shuffle.take(2).join
  end

  def number
    rand(100..999)
  end
end
