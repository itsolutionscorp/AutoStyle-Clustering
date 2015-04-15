class Phrase
  def initialize(words)
    @words = remove_bad_input(words)
  end

  def word_count
    @words.group_by { |word| word }.inject({}) do |hash, (word, list)|
      hash.merge(word => list.length)
    end
  end

  private
  def remove_bad_input(words)
    words.downcase.scan(/\w+/)
  end
end
