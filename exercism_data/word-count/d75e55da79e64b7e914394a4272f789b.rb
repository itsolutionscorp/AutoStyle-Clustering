class Phrase
  def initialize(words)
    @words = sanitize_input(words)
  end

  def word_count
    @words.group_by { |word| word }.
      inject({}) { |hash, (word, list)|
        hash.merge(word => list.length)
    }
  end

  private
  def sanitize_input(words)
    words.downcase.scan(/\w+/)
  end
end
