class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    word_count_hash = {}
    phrase.gsub!(/[^\w\d\s,']/, '')
    words = if !phrase.include?(" ")
      phrase.split(",")
    else
      phrase.delete(",").split
    end
    words_unique = words.uniq
    word_counts = words_unique.map { |word| words.count(word) }
    words_unique.each_with_index do |word, i|
      word_count_hash[word] = word_counts[i]
    end
    word_count_hash
  end
end
