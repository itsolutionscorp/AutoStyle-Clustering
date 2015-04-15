class Anagram
  attr_reader :subject

  def initialize(input)
    @subject = input
  end

  def match(potential_words)
    length_matches = same_length(potential_words)
    length_matches.select do |word|
      word.split(//).all? { |letter| subject.include?(letter) &&
                        subject.count(letter) == word.count(letter) }
    end
  end

private
  def same_length(potential_words)
    potential_words.select do |word|
      word.length == subject.length
    end
  end
end
#defeat...
