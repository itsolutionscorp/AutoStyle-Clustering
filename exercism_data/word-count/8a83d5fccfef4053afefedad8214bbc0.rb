class Words

  def initialize(source)
    @source = source
  end

  def count
    words.reduce(Hash.new(0)) do |word_counts, word|
      word_counts[word] += 1
      word_counts
    end
  end

  private

  attr_reader :source

  def words
    source.downcase.split(/\W+/)
  end

end
