class Robot
  attr_accessor :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    (('a'..'z').to_a.sample * 2) + (('1'..'9').to_a.sample * 3)
  end
end
