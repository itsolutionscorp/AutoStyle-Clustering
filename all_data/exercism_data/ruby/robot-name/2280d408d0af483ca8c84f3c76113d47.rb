class Robot
  attr_reader :name

  def initialize
    generate_name
  end

  def reset
    generate_name
  end

  private

  def generate_name
    @name = "#{alpha}#{alpha}#{digit}#{digit}#{digit}"
  end

  def alpha
    (('a'..'z').to_a + ('A'..'Z').to_a).sample
  end

  def digit
    ('0'..'9').to_a.sample
  end
end
