class Anagram
  attr_reader :initial_word

  def initialize(initial_word)
    @initial_word = initial_word
  end

  def match(potential_anagrams)
    potential_anagrams_without_self = potential_anagrams.reject do |anagram|
      anagram.downcase == initial_word.downcase
    end

    potential_anagrams_without_self.select do |potential_anagram|
      alphagram(potential_anagram) == initial_alphagram
    end
  end

  private

  def initial_alphagram
    @initial_alphagram ||= alphagram(initial_word)
  end

  def alphagram(word)
    word.downcase.chars.sort
  end
end
