class Phrase
  def initialize(text)
    text.downcase!
    
    @phrase = contains_whitespace?(text) ? Vanilla.new(text) : Cramped.new(text)
  end
  
  def word_count
    result = {}
    
    @phrase.each_word do |word|
      result[word] = 0 unless result[word]
      result[word] += 1
    end
    
    result
  end

  private

  def contains_whitespace?(text)
    text.match /\s/
  end
end

class Vanilla
  attr_reader :words

  def initialize(text)
    @words = text.gsub(/[^\w\s']/, '').split /\s+/
  end

  def each_word(&block)
    @words.each {|word| yield word} if block_given?
  end
end

class Cramped
  attr_reader :words

  def initialize(text)
    @words = text.split /,/
  end

  def each_word(&block)
    @words.each {|word| yield word} if block_given?
  end
end
