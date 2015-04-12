class String
  def words
    scan(/\w+/)
  end
end

module Aggregatable
  def frequency_by
    return enum_for __callee__ unless block_given?

    each_with_object(Hash.new(0)){ |w, counts|
      category = yield(w)
      counts[category] += 1
    }
  end
end

class Phrase
  def initialize(raw_string)
    @_words = normalize(raw_string).words
  end

  def word_count
    @word_count ||= words.extend(Aggregatable).frequency_by{ |w| w }
    @word_count.dup
  end

  def words
    @_words.dup
  end

  private
  def normalize(s)
    s.downcase
  end
end
