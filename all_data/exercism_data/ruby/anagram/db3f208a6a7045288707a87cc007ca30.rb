class Anagram
  def initialize(word)
    @word = word
  end

  def match(other_words)
    list_of_anagrams = []
    letters_in_word = @word.split(//)
    letters_in_word_count = {}

    letters_in_word.each do |letter|
      letters_in_word_count[letter.downcase] = 0
    end

    letters_in_word.each do |letter|
      letters_in_word_count[letter.downcase] +=1
    end

    i=0
    other_words.each do |other_word|
      letters_in_other_word = other_word.split(//)
      letters_in_other_word_count = {}

      letters_in_other_word.each do |letter|
        letters_in_other_word_count[letter.downcase] = 0
      end

      letters_in_other_word.each do |letter|
        letters_in_other_word_count[letter.downcase] +=1
      end

      if letters_in_word_count == letters_in_other_word_count && @word.downcase != other_word.downcase
        list_of_anagrams[i] = other_word
        i+=1
      end
    end
    list_of_anagrams
  end
end
