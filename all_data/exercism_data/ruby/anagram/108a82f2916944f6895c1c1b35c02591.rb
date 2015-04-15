class Anagram
  attr_accessor :subject

  def initialize(subject)
    @subject = subject
  end

  def match(word_array)
    remove_duplicate_words_in word_array
    word_array.select { |word| sorted_letters_of(word) == sorted_letters_of(subject) }
  end

  private

    def remove_duplicate_words_in(word_array)
      word_array.reject! { |word| word.downcase == subject.downcase }
    end

    def sorted_letters_of(input)
      input.downcase.chars.sort
    end

end
