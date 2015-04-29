class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(array_in_words)
    array_in_words = array_in_words
    word_in_letters = @word.split("")
    letters_array = []

    array_in_words.each do |element|
      if @word == element.downcase
        nil
      elsif word_in_letters.sort == element.downcase.split("").sort
        letters_array << element
      else
        letters_array
      end
    end
    letters_array
  end
end



# have word and array_to_match

# split word into letters and each element of array into letters

# match the word's letters to an element of array's letters
# using the sort method

# return that full word
