class Phrase

  attr_reader :sentence

  def initialize( sentence )
    @sentence = sentence.downcase
  end

  def split_into_words
    sentence.scan(/\w+/)
  end

  def word_count
    split_into_words.each_with_object(Hash.new(0)) do |word, hash| 
      hash[word] += 1 
    end
  end

end
