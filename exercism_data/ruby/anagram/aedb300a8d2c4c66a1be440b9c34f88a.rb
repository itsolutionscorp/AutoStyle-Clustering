class Anagram

  def initialize detector
    @detector = detector
  end

  def match input_words
    raise NameError, "Format should be in array []" if input_words.class != Array
    input_words.select do |word|
      anagram_of? word
    end
  end

  private

  def anagram_of? word
    if identical_word? word
      false
    else
      detect_word = Word.new(@detector.downcase).sort_characters
      detect_word.eql? Word.new(word.downcase).sort_characters
    end
  end

  def identical_word? word
    @detector.downcase.eql? word.downcase
  end

end

class Word

  def initialize word
    @word = word
  end

  def sort_characters
    @word.chars.sort.join
  end
end
