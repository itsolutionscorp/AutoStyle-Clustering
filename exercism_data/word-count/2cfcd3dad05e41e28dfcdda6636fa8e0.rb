class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words_count = {}

    words = phrase.scan(/(\w*)/).flatten
    groups = words.group_by { |word| normalize_word(word) }
    groups.map do |word, occurences|
      words_count[word] = occurences.size unless word.empty?
    end

    words_count
  end

  private

  def normalize_word(word)
    word.strip.downcase
  end
end
