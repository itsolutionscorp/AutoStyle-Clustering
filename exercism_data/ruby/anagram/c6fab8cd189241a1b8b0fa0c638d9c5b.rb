class Anagram
  def initialize word
    @word = Word.new(word)
  end

  def match words
    words
      .reject{ |word| word.nil? or @word.is_same_word? word }
      .select{ |word| @word.is_anagram? word }
  end
end

class Word 
  def initialize word
    @word = word.to_s
  end

  def is_anagram? word
    alphagram == (generate_alphagram word)
  end

  def is_same_word? word
    @word.downcase == word.downcase
  end

  private

  def alphagram
    @alphagram ||= generate_alphagram @word 
  end

  def generate_alphagram word
    word.downcase.chars.sort
  end
end
