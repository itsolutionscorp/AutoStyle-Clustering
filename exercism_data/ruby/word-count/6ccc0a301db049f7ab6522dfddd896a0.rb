class Phrase

  def initialize(words)
    @words = words
  end

  def word_count
    @words = @words.downcase
    @words.delete! ":.!&@$%^&"
    @words.gsub!(","," ")
    @split_words = @words.split
    counts = {}
    @split_words.each do |word|
      if counts[word] != nil
        counts[word] = counts[word] + 1
      else
        counts[word] = 1
      end
    end
   counts
  end
end
