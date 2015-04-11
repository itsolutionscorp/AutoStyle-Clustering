class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    occurrences = Occurrences.new
    words.each { |word| occurrences << word }
    occurrences.result
  end

  def words
    phrase.split(/[^'\w]/).reject { |word| word == '' }
  end
end

class Occurrences
  attr_reader :result

  def initialize
    @result = Hash.new { |hash, key| hash[key] = 0 }
  end

  def <<(word)
    result[normalize_case(word)] += 1
  end

  private

  def normalize_case(word)
    word.downcase
  end
end
