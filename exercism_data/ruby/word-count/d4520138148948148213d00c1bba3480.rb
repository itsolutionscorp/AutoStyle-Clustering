class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.downcase.scan(/\w+/).count_by
  end
end

module Enumerable
  # Returns a Hash keyed by the value of the block to the number times that
  # value was returned.  If you have experience with the #group_by from
  # ActiveSupport, this would be like .group_by(&block).map{|k,a|[k,a.size]}
  # (except it is a Hash rather than an Array).
  def count_by
    counts = Hash.new(0)
    each {|e| counts[block_given? ? yield(e) : e] += 1}
    counts
  end
end
