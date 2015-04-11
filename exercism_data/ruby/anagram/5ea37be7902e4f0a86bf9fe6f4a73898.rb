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
    letter_counter1 = count_letters(normalized_word)
    letter_counter2 = count_letters(word)

    letter_counter1 == letter_counter2 and word != normalized_word

  end

  def count_letters(word)

    letter_counter = Hash.new(0)
    word.each_char do |letter|
      letter_counter[letter] += 1
    end
    letter_counter

  end

end
