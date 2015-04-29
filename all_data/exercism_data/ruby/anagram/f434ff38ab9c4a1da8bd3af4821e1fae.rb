class Anagram
  attr_reader :subject

  def initialize subject
    @subject = subject.downcase
  end

  def match words
    matched_words = []
    words.each do |word|
      if subject.size == word.size
        if subject.chars.sort == word.downcase.chars.sort
          if word.downcase != subject
            matched_words.push word
          end
        end
      end
    end
    matched_words
  end
end
