class Anagram
  def initialize(input_word)
    @word = input_word.downcase
  end

  def match(candidate_terms)
    candidate_terms.reject(&identical_word).keep_if(&letters_match)
  end

  def identical_word
    proc { |term| term.downcase == @word }
  end

  def letters_match
    proc { |term| sort_letters(term) == sort_letters(@word) }
  end

  def sort_letters(term)
    term.downcase.chars.sort
  end
end
