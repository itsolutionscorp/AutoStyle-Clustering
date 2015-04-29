class Anagram
  def initialize word
    @word = word.to_s.downcase
  end

  def match words
    words
      .reject{ |word| word.nil? or is_same_word? word }
      .select{ |word| is_anagram? word }
  end

private
  def alphagram word
    word.chars.sort 
  end

  def alphagrammed_word
    @alphagrammed_word ||= alphagram @word
  end

  def is_anagram? word
    alphagrammed_word  == (alphagram word.downcase)
  end

  def is_same_word? word
    @word == word.downcase
  end
end
