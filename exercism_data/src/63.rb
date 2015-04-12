class Phrase

  def initialize(phrase)
    @container = phrase
  end

  def word_count
    hash = Hash.new(0)
    @container.downcase.scan(/[\w']+/).each do |word|
      hash[word] += 1
    end
    hash
  end

end
