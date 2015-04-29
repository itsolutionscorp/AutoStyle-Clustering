class Words

  def initialize(source)
    @source = source
  end

  def count
    words.each_with_object(Hash.new(0)) do |word, word_counts|
      word_counts[word] += 1
    end
  end

  private

  attr_reader :source

  def words
    source.downcase.split(/\W+/)
  end

end
