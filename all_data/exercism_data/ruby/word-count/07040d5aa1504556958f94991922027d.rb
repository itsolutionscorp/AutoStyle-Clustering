class Phrase

  def initialize(phrase)
    @phrase = phrase.split(',').join(' ').gsub(/[^0-9a-z' ]/i, '').downcase
  end
  def word_count
    @phrase.split().each_with_object(Hash.new(0)) { |word,counts| counts[word] += 1 }
  end
end
