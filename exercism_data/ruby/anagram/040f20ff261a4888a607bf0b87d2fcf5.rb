class String

  def to_array
    split(//)
  end

end


class Anagram

  def initialize(word)
    @original_word = word.downcase
    @word = word.downcase.to_array.sort
    @results = []
  end

  def match(words)
    words.each do |w|
      if w.downcase.to_array.sort == @word
        @results << w unless w.downcase == @original_word
      end
    end
    @results
  end

end
