class Anagram
  attr_reader :subject

  def initialize subject
    @subject = subject.downcase
  end

  def match words
    matched_words = []
    words.each do |word|
      word_copy = word.dup.downcase
      if subject.size == word_copy.size
        subject.chars.each do |c|
          index = word_copy.index(c)
          if index != nil
            word_copy.slice!(index)
          end
        end
        if word_copy.length == 0 && word.downcase != subject
        matched_words.push word
        end
      end
    end
    matched_words
  end
end
