class Phrase
  attr_reader :words, :count
  def initialize words
    @words = words.downcase.split(/[^\w']+/)
  end

  def word_count
    @count ||= @words.inject(Hash.new(0)) do |hash, word| 
      hash[word] += 1
      hash
    end
  end
end
