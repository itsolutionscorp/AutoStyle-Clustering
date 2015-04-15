class Phrase

  attr_reader :sentence

  def initialize( sentence )
    @sentence = SplittablePhrase.new( sentence )
  end

  def word_count
    
    sentence.split_words.inject( Hash.new(0) ) do |hash, word| 
      hash[word] = hash[word] + 1
      hash
    end

  end

end

class SplittablePhrase

  attr_reader :sentence

  def initialize( sentence )
    @sentence = sentence
  end

  def split_words    
    sentence.scan( /\w+/ ).map{ |word| word.downcase }
  end

end
