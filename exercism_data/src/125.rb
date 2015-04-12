class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    words_and_occurences = Hash.new(0)
    @words.downcase.scan(/[a-z0-9]+/) do |word|
      words_and_occurences[word] += 1
    end
    return words_and_occurences
  end
end
