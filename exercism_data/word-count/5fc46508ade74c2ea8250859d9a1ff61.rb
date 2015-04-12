class Phrase
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    word_list = word_list_from(
      string: coerce_to_words_and_whitespace(input: input))

    count_hash_value_lengths(word_list)
  end

  private
  def coerce_to_words_and_whitespace(input: nil)
    casted   = input.to_s.strip.downcase
    filtered = casted.gsub(/[^\w\s]/, ' ')
  end

  def word_list_from(string: nil)
    string.split.sort.group_by(&:to_s)
  end

  def count_hash_value_lengths(input_hash={})
    input_hash.reduce(input_hash) { |memo, (k, v)|
      memo.merge({ k => v.count })
    }
  end
end
