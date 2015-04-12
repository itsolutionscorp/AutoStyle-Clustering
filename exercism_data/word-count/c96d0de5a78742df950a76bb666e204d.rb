class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = split_words(@phrase)
    count_occurrences(words)
  end

  def split_words(phrase)
    phrase.downcase.scan(/\w+/)
  end

  def count_occurrences(words)
    words.each.with_object(Hash.new(0)) do |item, occurrences|
      occurrences[item] += 1
    end
  end
end
