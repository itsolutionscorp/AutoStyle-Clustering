class Phrase

  def initialize(words)
    @words = words
  end

  def word_count
    counts = Hash.new(0)
    split_words.each do |word|
      counts[word] += 1
    end
    counts
  end

  # Return an array of words from the original input string.
  # They will be:
  #  - case normalized (lower case)
  #  - split on non-word-char boundaries (non-digit, non-letter)
  #  - no empty strings will be returned
  def split_words
    @words.downcase
          .split(/\W/)
          .reject {|s| s.empty? }
  end

end
