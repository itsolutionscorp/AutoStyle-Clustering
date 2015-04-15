class Phrase
  def initialize source
    @source = source.to_s
    @estimations = Hash.new do |count_storage, word|
      count_storage[word] = 0
    end
  end

  def word_count
    return estimations if already_estimated?
    estimate_words do |word|
      increase_count_of word
    end
  end

private
  attr_reader :source, :estimations

  def already_estimated?
    estimations.any?
  end

  def estimate_words &block
    get_words.each(&block)
    estimations
  end

  def get_words
    source.split(/\W+/)
  end

  def increase_count_of word
    estimations[word.downcase] += 1
  end
end
