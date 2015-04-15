class Anagram

  attr_reader :word

  def initialize word
    @word = word  
  end

  def match possible_anagrams
    possible_anagrams.select do |candidate_word|
      word_characters_equal?( candidate_word ) && word_differs?( candidate_word )
    end
  end

private
  
  def word_characters_equal? candidate_word 
    ordered_chars_of( candidate_word ) == seed  
  end

  def seed
    @seed ||= ordered_chars_of word
  end
  
  def ordered_chars_of word
    word.downcase.chars.sort
  end

  def word_differs? another_word
    another_word.downcase != word
  end

end
