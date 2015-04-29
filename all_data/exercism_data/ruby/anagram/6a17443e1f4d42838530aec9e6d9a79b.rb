class Anagram

  def initialize(sentence)
    @sentence = sentence
    @result = []
  end

  def match(words)
    test = sort_sentence
    words.each do |word|
      word_test = word.downcase.chars.sort.join
      if word_test == test && @sentence.downcase != word.downcase
        @result << word
      end
    end
    @result
  end

  private
  def sort_sentence
    @sentence.downcase.chars.sort.join
  end

end
