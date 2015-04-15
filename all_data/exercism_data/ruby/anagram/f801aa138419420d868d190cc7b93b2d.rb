class Anagram
  def initialize(subject)
    @subject = Anagrammable.new(subject)
  end

  def match(candidate_words)
    candidate_words
      .map{ |word| Anagrammable.new(word) }
      .select{ |word| word.anagram_of? @subject }
  end

  class Anagrammable < String
    def anagram_of?(other)
      (letters == other.letters) && (downcase != other.downcase)
    end

    protected

    def letters
      downcase.chars.sort
    end
  end
end
