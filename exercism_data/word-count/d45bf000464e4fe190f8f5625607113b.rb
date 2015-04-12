class Phrase
  EXCLUDE = /[^a-z\d\s]/

  def initialize(words)
    @words = words
  end

  def words
    @words.downcase.gsub(EXCLUDE, '').split(' ')
  end

  def word_count
    word_counts = {}

    words.each do |word|
      word_counts[word] = word_counts[word].to_i + 1
    end

    word_counts
  end
end
