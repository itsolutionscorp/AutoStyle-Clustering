class Phrase
  def initialize(phrase)
    @words = extract_words(phrase)
  end

  def word_count
    @words.inject({}) {|counts, word| counts[word] = counts[word].to_i + 1; counts}
  end

  private

  def extract_words(phrase)
    normalize_case remove_punctuation split_in_words(phrase)
  end

  def split_in_words(phrase)
    phrase.split(/[ |,]/)
  end

  def remove_punctuation(words)
    words.map{|word| word.scan(/\w+/).first}.compact
  end

  def normalize_case(words)
    words.map{|word| word.downcase}
  end

end
