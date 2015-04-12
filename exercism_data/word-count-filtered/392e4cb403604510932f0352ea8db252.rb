class Phrase

  attr_reader :sentence

  def initialize( sentence )
    @sentence = sentence.downcase
  end

  def word_count
    sentence.scan( /\w+/ ).each_with_object( Hash.new(0) ) do |word, hash| 
      hash[word] += 1 
    end
  end

end
