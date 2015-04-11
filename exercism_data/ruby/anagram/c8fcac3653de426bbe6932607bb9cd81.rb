class Anagram
  attr_reader :word, :possible_anagrams

  def initialize(word)
    @word = word
  end

  def match(possible_anagrams)
    @possible_anagrams = possible_anagrams
    compare_words
  end

  private
    def compare_words
      a, b = remove_duplicates.select do |w|
        sort_letters_in(w) == sort_letters_in(word)
      end
    end

    def sort_letters_in(word)
      word.downcase.chars.sort
    end

    def remove_duplicates
      possible_anagrams.select { |w| w.downcase != word.downcase }
    end
end
