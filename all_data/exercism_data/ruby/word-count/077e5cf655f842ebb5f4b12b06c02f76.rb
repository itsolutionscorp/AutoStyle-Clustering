class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    hash = Hash.new(0)
    split_phrase.each do |word|
      hash[word] += 1
    end
    return hash
  end

  def split_phrase
    @phrase.scan(/\w+/)
  end

end
