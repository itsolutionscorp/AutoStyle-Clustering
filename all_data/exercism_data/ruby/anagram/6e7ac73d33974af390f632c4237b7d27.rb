class Anagram

  def initialize detector
    @detector = detector.downcase
  end

  def match input_words
    raise NameError, "Format should be in array []" if input_words.class != Array
    input_words.select do |word|
      anagram_of? word.downcase
    end
  end

  private

  def anagram_of? word
    if identical_word? word
      false
    else
      Word.sort_characters(@detector).eql? Word.sort_characters(word)
    end
  end

  def identical_word? word
    @detector.eql? word
  end

end

class Word
  def self.sort_characters word
    word.chars.sort
  end
end
