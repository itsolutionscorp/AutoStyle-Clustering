class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= calc_word_count
  end

  private

  def calc_word_count
    words.each_with_object(Hash.new(0)) do |word, word_counts|
      word_counts[word] += 1
    end
  end

  # Tokenize phrase into normalized words.
  # Punctuation will be stripped from words.
  # Spaces and commas are valid separators.
  #
  # E.G. "word1 word2 word3,word4" will parse to
  # [word1, word2, word3, word4]
  #
  def words
    @phrase.
      split(/[\n ,]/).
      map {|w| normalize_word(w) }.
      delete_if {|w| w.empty? }
  end

  def normalize_word(word)
    word.gsub(/[.:!&@$%^]/,'').downcase
  end
end
