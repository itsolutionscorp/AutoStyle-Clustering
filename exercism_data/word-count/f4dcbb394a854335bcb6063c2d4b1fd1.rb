require 'pry'
class Phrase

  def initialize(input_phrase)
    @phrase=input_phrase

  end

  def to_s
    return @phrase.to_s
  end



  def word_count
    output_hash= {}

    
    phrase_slush = @phrase.clone

    phrase_slush = self.clean_string_of_junk_right(phrase_slush)

    while phrase_slush.length > 0 do
      phrase_slush = self.clean_string_of_junk_left(phrase_slush)
      word = select_word_from_phrase(phrase_slush)
      phrase_slush.slice!(0..word.length)               #chop the word off of the front of the phrase
      
      #build the hash  #FIX consolidate these two steps somehow
      if output_hash[word]
        output_hash[word] = output_hash[word]+1
      else
        output_hash[word] = 1
      end

    end

    return output_hash

  end

  # private 

  


  def clean_string_of_junk_left(dirty_phrase)
    # while  (dirty_phrase.length > 0) && (!/\w+/.match(dirty_phrase[0]))
    while  (!/\w+/.match(dirty_phrase[0]))
      dirty_phrase.slice!(0)
    end
    
    return dirty_phrase #now clean
  end

  def clean_string_of_junk_right(dirty_phrase)
    while  (!/\w+/.match(dirty_phrase[-1]))
      dirty_phrase.slice!(-1)
    end
    
    return dirty_phrase #now clean
  end

  def select_word_from_phrase(local_phrase)
    # only works on clean phrases
    # word = /\w+/.match(local_phrase).to_s             #matches words
    # word = /(\w+'?\w?)/.match(local_phrase).to_s      #matches wors with apostrophie
    word = /([\w0-9]+'?\w?)/.match(local_phrase).to_s #matches words, and numbers too
    word.downcase!
    return word
  end

end
