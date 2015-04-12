class Phrase
  attr_reader :text

  def initialize(text)
    @text = text
  end

  # Logic all in one method
  def word_count_all_in_one
    Hash[text.downcase.scan(/\w+/).group_by { |word| word }.map { |word, words| [word, words.size] }]
  end

  def word_count
    Hash[words_and_counts]
  end

  def words_and_counts
    words_grouped_by_word.map { |word, words| [word, words.size] }
  end

  def words
    text.downcase.scan(/\w+/)
  end

  def words_grouped_by_word
    words.group_by { |word| word }
  end
end
