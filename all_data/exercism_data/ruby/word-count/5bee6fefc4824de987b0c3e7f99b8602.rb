class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = extract_words(phrase)
    counts = Hash.new(0)

    words.each { |word| counts[word] += 1 }
    counts
  end

  private

  def extract_words(phrase)
    phrase.downcase
          .split(/[^a-zA-Z0-9_']/)
          .select { |word| !word.empty? }
  end
end
