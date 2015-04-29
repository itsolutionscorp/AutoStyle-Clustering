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
    @name = NameGenerator.new.generate
  end
end

class NameGenerator
  def initialize(options = {})
    @num_chars  = options.fetch(:num_chars, 2)
    @num_digits = options.fetch(:num_digits, 3)
  end

  def generate
    random_chars + random_digits
  end

  private

  def random_chars
    ("A".."Z").to_a.sample(@num_chars).join
  end

  def random_digits
    (1..9).to_a.sample(@num_digits).join
  end
end
