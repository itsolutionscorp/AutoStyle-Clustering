class Phrase

  attr_reader :sentence

  def initialize( sentence )
    @sentence = SplittablePhrase.new( sentence )
  end

  def word_count

    count = Hash.new

    sentence.split_words.each do |word|
      if count.has_key? word
        count[word] = count[word] + 1
      else
        count[word] = 1
      end
    end

    count

  end

end

class SplittablePhrase

  attr_reader :sentence

  def initialize( sentence )
    @sentence = sentence
  end

  def split_words

    split_sentence = Array.new
    
    sentence.scan( /\w+/ ).each do |word|
      split_sentence.push word.downcase
    end

    split_sentence

  end


end
