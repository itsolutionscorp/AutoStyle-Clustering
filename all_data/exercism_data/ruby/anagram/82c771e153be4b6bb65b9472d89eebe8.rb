class Anagram
  def initialize term
    @term = term
  end

  def match words
    words.each_with_object([]) { |word, matches| 
      matches << word if anagram? word
    }
  end

  private

  def anagram? word
    letter_counts(word) == letter_counts(@term)
  end

  def letter_counts word
    result = Hash.new 0
    word.each_char { |c| result[c] += 1 }
    result
  end
end
