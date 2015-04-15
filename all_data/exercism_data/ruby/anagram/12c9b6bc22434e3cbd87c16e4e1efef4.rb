class Anagram
  attr_reader :subject

  def initialize(input)
    @subject = input
  end

  def match(potential_words)
    # given an array of potential words
      # iterate through them and select those with the same collection of letters as the subject
    length_matches = same_length(potential_words)
    potential_words.select do |word|
      is_anagram_to_subject(word)
    end
  end

private
  def is_anagram_to_subject(word)
    word.chars =~ subject.chars
  end

  def same_length(potential_words)
    potential_words.select { |word| subject_length_matches(word) }
  end

  def subject_length_matches(word)
    word.length == subject.length
  end
end


#this isn't done, I submitted by accident
