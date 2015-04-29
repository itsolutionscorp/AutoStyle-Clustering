class Phrase
  attr_reader :input

  def initialize(input)
    @input = sanitize(input)
  end

  def word_count
    @word_count ||= count_words
  end

  private

  def count_words
    each_word do |word|
      counts[word.downcase] += 1
    end
    counts
  end

  def each_word
    input.split.each do |word|
      yield word
    end
  end

  def counts
    @counts ||= new_counts_hash
  end

  def sanitize(input)
    input.gsub(/,/, ' ').gsub(/[^\w\s]/, '')
  end

  def new_counts_hash
    Hash.new do |h, k|
      h[k] = 0
    end
  end
end
