class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(potential_anagrams)
    potential_anagrams_without_subject = potential_anagrams.reject do |anagram|
      anagram.downcase == subject.downcase
    end

    potential_anagrams_without_subject.select do |potential_anagram|
      alphagram(potential_anagram) == initial_alphagram
    end
  end

  private

  def initial_alphagram
    @initial_alphagram ||= alphagram(subject)
  end

  def alphagram(word)
    word.downcase.chars.sort
  end
end
