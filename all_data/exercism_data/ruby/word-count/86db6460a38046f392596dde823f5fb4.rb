class Phrase

  def initialize(content)
    @content = content
  end

  def words
    @content.split(/\W+/)
  end

  def word_count
    @word_counts ||= calculate_word_counts
  end

  private

  def calculate_word_counts
    normalized_words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1}
  end

  def normalized_words
    words.map(&:downcase)
  end

end
