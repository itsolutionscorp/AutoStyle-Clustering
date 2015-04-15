class Words

  def initialize(source)
    @source = source
    @word_counts = Hash.new(0)
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
    source.downcase.split(/\W+/)
  end

  def count_word(word)
    word_counts[word] += 1
  end

  def reset_word_counts
    self.word_counts = Hash.new(0)
  end

end
