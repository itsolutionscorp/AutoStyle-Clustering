class Anagram

  def initialize(subject)
    @subject = subject
  end

  def match(word_array)
    remove_duplicate_words(word_array).select do |word| 
      sort_letters_of(word) == sort_letters_of(@subject)
    end
  end

  private
    def remove_duplicate_words(word_array)
      word_array.reject { |word| word.downcase == @subject.downcase }
    end

    def sort_letters_of(input)
      input.downcase.chars.sort
    end
end
