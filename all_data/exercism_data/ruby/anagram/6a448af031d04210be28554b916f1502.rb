class Anagram

  def initialize(word)
    @initial_word = word
  end

  def match(word_list)

    word_list.each_with_object(Array.new) do |word, anagrams|
      if(self.isAnagram?(word))
        anagrams.push(word)
      end
    end

  end

  def normalized_word
    @initial_word.downcase
  end

  def isAnagram?(word)

    word.downcase!
    word.chars.sort == normalized_word.chars.sort and word != normalized_word

  end

end
