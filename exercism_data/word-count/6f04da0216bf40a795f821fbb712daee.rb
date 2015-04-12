class Phrase
  def initialize(phrase)
    @phrase=String(phrase)
  end
  def word_count
    word_counts = {}
    word_counts.default=0
    sanitize
    words = tokenize
    words.each do |word|
      word_counts[word] = word_counts[word] + 1
    end    
    word_counts
  end
  def sanitize
    @phrase.downcase!
    @phrase.gsub!(/[^a-z0-9 ,]/,'')
  end
  def tokenize
    @phrase.split(/[\s,]+/)
  end
end
