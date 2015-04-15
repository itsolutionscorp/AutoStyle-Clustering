##
# Class that, given a word and a list of possible anagrams,
# selects the correct sublist.
class Anagram
  attr_reader :subject

  def initialize(word)
    @subject = word.downcase
  end

  def match(words)
    words.map { |word| create_checklist(word) }
         .select { |checklist| anagram?(checklist) }
         .map { |checklist| checklist.word }
  end

  private

  Checklist = Struct.new(:word, :sorted_chars)

  def subject_sorted_chars
    @subject_sorted_chars ||= @subject.chars.sort
  end

  def anagram?(checklist)
    checklist.word.downcase != @subject &&
      checklist.sorted_chars == subject_sorted_chars
  end

  def create_checklist(word)
    Checklist.new(word, word.downcase.chars.sort)
  end
end
