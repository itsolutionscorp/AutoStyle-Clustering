class Phrase

  def initialize(content)
    @content = content
  end

  def words
    @content.split(/\W+/)
  end

  def word_count
    @word_counts || calculate_word_counts
  end

  private

  def calculate_word_counts
    @word_counts = normalized_words.inject(Hash.new(0)) { |hash, word| hash[word] += 1; hash}
  end

  def normalized_words
    words.map(&:downcase)
  end

end
