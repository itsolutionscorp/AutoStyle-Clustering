class Phrase
  def initialize(phrase)
    @phrase = phrase
    @words = cleanse_and_break
  end
  
  def word_count
    counts = Hash.new
    @words.each do |word|
      if counts.empty? || !counts.has_key?(word)
        counts.store(word, 1)
      else
        counts[word] = counts[word].to_i + 1
      end
    end
    counts
  end
  
  private
  
  def cleanse_and_break
    @phrase.gsub(/[^a-zA-Z0-9\']/, " ").downcase.split(" ")
  end
end
