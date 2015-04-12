class Phrase

  attr_reader :sentence

  def initialize( sentence )
    @sentence = SplittablePhrase.new( sentence )
  end

  def word_count
    sentence.split_words.each_with_object( Hash.new(0) ) { |word, hash| hash[word] = hash[word] + 1 }
  end

end

class SplittablePhrase

  attr_reader :sentence

  def initialize( sentence )
    @sentence = sentence.downcase
  end

  def split_words    
    sentence.scan( /\w+/ )
  end

end
