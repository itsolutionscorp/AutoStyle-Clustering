class Robot

  attr_reader :name

  CHARS = ("A".."Z").to_a + ("a".."z").to_a
  DIGITS = (0..9).to_a
  REGISTRY = []

  def initialize
    reset
  end

  def reset
    REGISTRY.delete(@name) if @name
    @name = generate_name
  end

  private
  def generate_name
    new_name = (CHARS.shuffle[0,2] + DIGITS.shuffle[0,3]).join
    return generate_name if REGISTRY.index(new_name)
    REGISTRY << new_name
    new_name
  end

end
