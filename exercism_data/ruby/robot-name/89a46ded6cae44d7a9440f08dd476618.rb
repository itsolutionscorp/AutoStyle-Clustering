class Robot
  CHARS = *('A'..'Z')

  def initialize
    @cache = []
  end

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_unique_name
    loop do
      name = generate_name
      break unless @cache.include?(name)
    end

    @cache << name

    name
  end

  def generate_name
    (random_chars(2) + random_numbers(3)).join
  end

  def random_chars(count)
    count.times.map { CHARS.sample }
  end

  def random_numbers(count)
    count.times.map { rand(9) }
  end
end
