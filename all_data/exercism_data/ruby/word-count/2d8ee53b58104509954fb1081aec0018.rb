module ExtendedEnumerable
  def frequency_by(&block)
    return enum_for __callee__ unless block_given?

    each_with_object(Hash.new(0)){ |w, counts| counts[yield w] += 1 }
  end
end

class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    @word_count ||= words.extend(ExtendedEnumerable).frequency_by{ |w| w }
    @word_count.dup
  end

  def words
    phrase.scan(/\w+/)
  end

  def to_s
    phrase.dup
  end

  private
  attr_reader :phrase
end
