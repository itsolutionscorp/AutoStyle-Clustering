class Phrase
  attr_reader :words, :count
  def initialize words
    @words = words.downcase.split(/[^\w']+/)
  end

  def word_count
    @count ||= @words.inject({}) do |hash, word| 
      if hash.include?(word)
        hash[word] += 1
      else
        hash[word] = 1
      end
      hash
    end
  end
end
