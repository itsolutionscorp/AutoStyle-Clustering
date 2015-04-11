class Robot
  attr_reader :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def random_char
    ('a'..'z').to_a.sample
  end

  def generate_name
    random_char + random_char + rand(10).to_s + rand(10).to_s + rand(10).to_s
  end
end
