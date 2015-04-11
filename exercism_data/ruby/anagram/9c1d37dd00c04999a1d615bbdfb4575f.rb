class Anagram

  class Word
    def initialize(str)
      @str = str
      @downcase_str = str.downcase
      @sorted_str = sort_chars(str)
    end

    def is_anagram_of?(str)
      !is_same?(str) && @sorted_str == sort_chars(str)
    end

    def is_same?(str)
      @downcase_str == str.downcase
    end

    def sort_chars(str)
      str.downcase.chars.sort.join
    end
  end


  def initialize(chars)
    @word = Word.new(chars)
  end

  def match(possible_anagrams)
    possible_anagrams.select do |possibility|
      @word.is_anagram_of?(possibility)
    end
  end

end
