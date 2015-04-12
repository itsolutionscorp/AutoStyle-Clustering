class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words_count = {}

    words = phrase.split /\s|:|,/
    groups = words.group_by { |word| normalize_word(word) }
    groups.each do |word, occurences|
      next if word.empty?

      words_count[word] = occurences.size
    end

    words_count
  end

  private

  def normalize_word(word)
    cleaned_word = word.match(/(\w*)/).captures.first

    cleaned_word.strip.downcase
  end
end
