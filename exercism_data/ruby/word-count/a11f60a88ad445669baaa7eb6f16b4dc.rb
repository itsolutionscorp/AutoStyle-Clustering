class Words

  def initialize(source)
    @source = source
  end

  def count
    reset_word_counts
    words.each do |word|
      count_word(word)
    end
    word_counts
  end

  private

  attr_reader :source
  attr_accessor :word_counts

  def words
    source.split(/\W+/).map { |word| word.downcase }
  end

  def count_word(word)
    if word_counts.has_key?(word)
      word_counts[word] += 1
    else
      word_counts[word] = 1
    end
  end

  def reset_word_counts
    self.word_counts = {}
  end

end
