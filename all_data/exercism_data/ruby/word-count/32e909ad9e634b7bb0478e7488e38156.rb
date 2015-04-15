class Phrase

  attr_accessor :content, :word_counts

  def initialize(content)
    @content = content
  end

  def words
    content.gsub(/\W/, " ").split
  end

  def normalized_words
    words.map(&:downcase)
  end

  def word_count
    word_counts || calculate_word_counts
  end

  private

  def calculate_word_counts
    @word_counts = {}
    normalized_words.each do |word|
      increment(word)
    end
    @word_counts
  end

  def increment(word)
    if @word_counts[word]
      @word_counts[word] += 1
    else
      @word_counts[word] = 1
    end
  end

end
