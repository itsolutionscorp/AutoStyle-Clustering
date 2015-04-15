class Anagram
  attr_reader :original

  def initialize original
    @original = original
  end

  def match candidates
    candidates.find_all { |candidate| anagram? candidate }
  end

  private

  def anagram? candidate
    candidate.size == original.size && letters(candidate) == original_letters
  end

  def original_letters
    @original_letters ||= letters original
  end

  def letters word
    word.downcase.chars.sort
  end
end
