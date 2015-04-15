class Anagram

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(words)
    AnagramAnalyzer.new(words).match(word)
  end
end

class AnagramAnalyzer
  
  attr_reader :anagrams

  def initialize(words)
    @anagrams = analyze(words)
  end

  def match(word)
    anagrams[key_for(word)]
  end


  private

  def key_for(word)
    word.downcase.chars.sort
  end

  def analyze(words)
    words.each_with_object(Hash.new {|hash, key| hash[key] = []}) do |word, anagrams| 
      anagrams[key_for(word)] << word
    end
  end
end
