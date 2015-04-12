class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    hash = Hash.new(0)
    @phrase.downcase.scan(/\w+['|-]\w+|\w+/).each do |word|
      hash[word] += 1
    end
    hash
  end
end
