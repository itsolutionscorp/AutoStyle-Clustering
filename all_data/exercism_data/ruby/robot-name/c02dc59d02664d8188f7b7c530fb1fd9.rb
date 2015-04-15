class Robot

  attr_reader :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    random_char + random_char + rand(999).to_s.ljust(3, ?0)
  end

  def random_char
    CHARS[rand(CHARS.size)]
  end

  CHARS = 'A'.upto('Z').to_a

end
