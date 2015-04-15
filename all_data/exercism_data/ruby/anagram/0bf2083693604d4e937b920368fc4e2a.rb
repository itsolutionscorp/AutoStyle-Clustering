class Anagram
  attr_reader :subject

  def initialize(input)
    @subject = input
  end

  def match(potential_words)
    length_matches = same_length(potential_words)
    length_matches.select do |word|
      word.chars.all? { |letter| subject.count(letter) == word.count(letter) }
    end
  end

private
  def same_length(potential_words)
    potential_words.select { |word| subject_length_matches(word) }
  end

  def subject_length_matches(word)
    word.length == subject.length
  end
end
