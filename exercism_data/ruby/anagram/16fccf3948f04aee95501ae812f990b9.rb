class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(potential_anagrams)
    potential_anagrams
      .reject(&method(:is_equivalent_to_subject))
      .select(&method(:matches_subject_alphagram))
  end

  private

  def is_equivalent_to_subject(word)
    word.downcase == subject.downcase
  end

  def matches_subject_alphagram(word)
    alphagram(word) == initial_alphagram
  end

  def initial_alphagram
    @initial_alphagram ||= alphagram(subject)
  end

  def alphagram(word)
    word.downcase.chars.sort
  end
end
