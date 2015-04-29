class Phrase
  attr_reader :words, :word_count
  def initialize words
    @words = words.downcase.split(/[^\w']+/)
  end

  def word_count
    @word_count ||= @words.inject(Hash.new(0)) do |hash, word| 
      hash[word] += 1
      hash
    end
  end
end
