class Words

  def initialize(words)
    @words = words || ""
  end

  def count
    word_counts = Hash.new(0)

    clean_words.each do |word|
      word_counts[word] += 1
    end

    word_counts
  end

  private

  def clean_words
    @words.downcase
          .gsub(/\W/, " ")
          .split
  end

end
