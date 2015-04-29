class Phrase
  def initialize(words)
    @words = words
    @words1 = @words.gsub(/,/, ' ').gsub(/!!\&\@\$%\^&/, '').gsub(/:/, '').downcase.gsub(/\./, '')
    
    @words2 = @words1.split
    @words2
  end
  
  def word_count
    
    counts = Hash.new 0
    @words2.each do |word|
    counts[word] += 1
    end
    counts
  end
end

phrase = Phrase.new("how','much,wood,could,a,wood,chuck,chuck if a wood chuck could chuck wood")

# phrase.word_count
