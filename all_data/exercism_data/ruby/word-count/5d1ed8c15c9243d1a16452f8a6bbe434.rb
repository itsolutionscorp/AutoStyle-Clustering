require 'strscan'

class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    group_words.each_with_object({}) do |(k,v),h|
      h[k] = v.size
    end
  end

  private
  def words
    @input.split(/\W+/)
  end

  def group_words
    words.group_by { |word| word.downcase }
  end
end
