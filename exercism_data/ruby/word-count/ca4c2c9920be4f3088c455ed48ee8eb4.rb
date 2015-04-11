class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    words = split_words(@phrase)
    count_occurrences(words)
  end

  def split_words(phrase)
    phrase.scan(/\w+/)
  end

  def count_occurrences(words)
    words.uniq.each.with_object(Hash.new(0)) do |word, occurrences|
      words.each do |item|
        occurrences[item] += 1 if item == word
      end
    end
  end
end
