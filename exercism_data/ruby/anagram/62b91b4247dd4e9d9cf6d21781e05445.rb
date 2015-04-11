class Anagram

  attr_reader :word

  def initialize word
    @word = word  
  end

  def match possible_anagrams
    possible_anagrams.select do |candidate_word|
      is_anagram? candidate_word
    end
  end

  def is_anagram? candidate_word
    word_characters_equal?( candidate_word ) unless identical_word?( candidate_word )
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

  def identical_word? another_word
    another_word.casecmp( word ).zero?
  end

end
      
