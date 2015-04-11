class Anagram

  def initialize(subject)
    @subject = normalize(subject)
  end

  def match(possible_matches)
    possible_matches.uniq.map(&:downcase).select do |test_word| 
      anagram?(test_word)
    end
  end

  private

  def normalize(word)
    word.downcase
  end

  def anagram?(word)
    !exact_word_match?(word) && characters_match?(word)
  end

  def exact_word_match?(word)
    word == @subject 
  end

  def characters_match?(word)
    word.chars.sort == subject_characters
  end

  def subject_characters
    @subject_characters ||= @subject.chars.sort
  end

end
