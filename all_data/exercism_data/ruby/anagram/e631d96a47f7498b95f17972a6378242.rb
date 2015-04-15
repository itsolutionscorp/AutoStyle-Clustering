class Anagram

  attr_reader :subject, :words

  def initialize(subject)
    @subject = subject
  end

  def match(words)
    @words = words
    (anagrams - perfect_matches)
  end

  private

  def perfect_matches
    perfect_match = []
    words.each do |each_word|
      if each_word.downcase.chars == subject.downcase.chars
         perfect_match << each_word
      end
    end
    perfect_match
  end

  def anagrams
    anagrams = []
    words.each do |each_word|
      each_word.chars.sort 
      if each_word.downcase.chars.sort == subject.downcase.chars.sort
        anagrams << each_word
      end
    end
    anagrams
  end

end
