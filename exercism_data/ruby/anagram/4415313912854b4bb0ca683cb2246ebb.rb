class Anagram
  def initialize(input_word)
    @word    = input_word.downcase
    @letters = letters_in_sequence(@word)
  end

  def match(candidate_terms)
    candidate_terms.keep_if do |term| 
      letters_in_sequence(term) == @letters && term.downcase != @word
    end
  end

  def letters_in_sequence(term)
    term.downcase.split('').sort
  end
end
