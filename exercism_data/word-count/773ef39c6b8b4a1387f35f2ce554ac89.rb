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
    @word_counts = Hash.new(0)
    normalized_words.each do |word|
      @word_counts[word] += 1
    end
    @word_counts
  end

  def normalized_words
    words.map(&:downcase)
  end

end
