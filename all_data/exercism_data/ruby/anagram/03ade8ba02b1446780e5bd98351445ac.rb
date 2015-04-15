class Anagram
  def initialize(phrase)
    @phrase = phrase.downcase
    @phrasecount = charcount phrase
  end

  def match(words)
    words.collect { |word| matches(word) }.compact
  end

  def matches(word) 
    if charcount(word) == @phrasecount and not word.downcase == @phrase
      word 
    else 
      nil 
    end
  end

  def charcount(word)
    word
      .downcase
      .scan(/./)
      .each_with_object( Hash.new(0)) do |c, h| 
        h[c] += 1 
      end
  end

end
