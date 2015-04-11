class Phrase
  def initialize(phrase)
    @words = normalize_phrase phrase
  end

  def word_count
    counter = Hash.new(0)
    @words.scan(/\w+/) { |word| counter[word] += 1 }
    counter
  end

  private

  def normalize_phrase phrase
    phrase.to_s.downcase
  end
end
