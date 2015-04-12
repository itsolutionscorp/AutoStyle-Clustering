class Phrase
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    words_only = coerce_to_words_only(string: input)
    word_list  = word_list_from(string: words_only)

    count_hash_value_lengths(hash: word_list)
  end

  private
  def coerce_to_words_only(string: nil)
    casted   = input.to_s.strip.downcase
    filtered = casted.gsub(/[^\w\s]/, ' ') # words and whitespace
  end

  def word_list_from(string: nil)
    string.split.sort.group_by(&:to_s)
  end

  def count_hash_value_lengths(hash: nil)
    hash.reduce(hash) { |memo, (k, v)|
      memo.merge({ k => v.count })
    }
  end
end