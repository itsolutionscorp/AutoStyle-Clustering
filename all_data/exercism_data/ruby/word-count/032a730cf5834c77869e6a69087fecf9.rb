class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = split_words(@phrase)
    count_occurrences(words)
  end

  def split_words(phrase)
    phrase.scan(/\w+/)
  end

  def unique_words(words)
    words.map{|w| w.downcase}.uniq
  end

  def count_occurrences(words)
    unique_words(words).each.with_object({}) do |word, occurrences|
      words.each do |item|
        occurrences[item.downcase] = 0 if occurrences[item.downcase].nil?
        occurrences[item.downcase] += 1 if item.downcase == word.downcase
      end
    end
  end
end
