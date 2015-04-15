class Phrase
  def initialize (phrase)
    @phrase = phrase.downcase.gsub(/[^a-z\s\'0-9]/, ' ').split(" ")
  end
  
  def word_count
    counts = {}
    counts.default = 0
    
    @phrase.each do |word|
      counts[word] > 0? counts[word] += 1 : counts[word] = 1
    end
    
    counts
  end
end
