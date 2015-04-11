class Phrase
  def initialize(text)
    @text = text 
  end

  def word_count
    count(words = tokenise)
  end

  private

  def tokenise
    Words.collect_from @text
  end

  def count(words)
    Reader.new.collect(words)
  end
end

class Words
  def self.collect_from(text)
    text.scan(/\w+/).to_a
  end
end

class Reader
  def collect(words=[])
    Tally.new.tap do |tally|
      words.each{|w| tally.increment(w)}
    end.counts
  end
end

class Tally
  attr_reader :counts

  def initialize
    @counts = {}
  end

  def increment(word)
    key = word.downcase
    
    ensure_key key
    
    @counts[key] += 1
  end

  def ensure_key(key)
    @counts[key] = 0 unless @counts[key]
  end
end
