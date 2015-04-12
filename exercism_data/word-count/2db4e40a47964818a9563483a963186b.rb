class Phrase
  def initialize(phrase)
    @words = extract_words(phrase)
  end

  def word_count
    @words.each_with_object({}) {|word, counts| counts[word] = counts[word].to_i + 1}
  end

  private

  def extract_words(phrase)
    normalize_case split_in_words(phrase)
  end

  def split_in_words(phrase)
    phrase.scan(/\w+/)
  end

  def normalize_case(words)
    words.map{|word| word.downcase}
  end

end
