class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = {}

    case_insensitive_words_from_phrase.each do |word|
      count[word] ||= 0
      count[word] += 1
    end

    return count
  end

  private

    def case_insensitive_words_from_phrase
      words, buffer = [], []

      phrase.length.times do |index|
        char = phrase.slice(index)
        if char.match(/[a-zA-Z0-9]/) # word characters
          buffer << char
        elsif char.match(/[,|:|;| |\|]/) # separators
          words << buffer.join unless buffer.empty?
          buffer.clear
        end
      end
      words << buffer.join

      return words.map { |w| w.downcase }
    end
end
