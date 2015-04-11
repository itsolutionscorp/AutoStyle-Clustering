class Anagram
  def initialize word
    @word = Word.new(word)
  end

  def match words
    words
      .reject{ |word| word.nil? or @word.same_word? word }
      .select{ |word| @word.anagram? word }
  end
end

class Word 
  def initialize word
    @word = word.to_s.downcase
  end

  def anagram? word
    alphagram == (generate_alphagram word)
  end

  def same_word? word
    @word == word.downcase
  end

  private
  def alphagram
    @alphagram ||= generate_alphagram @word 
  end

  def generate_alphagram word
    word.downcase.chars.sort
  end
end
