class Robot
  WORD_CHARS = [0..9, 'a'..'z', ['_']].map(&:to_a).flatten

  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = generate_name
  end

  private

  def random_word_chars
    WORD_CHARS.sample(2).join
  end

  def random_number_code
    rand(100...1000).to_s
  end

  def generate_name
    random_word_chars + random_number_code
  end
end
