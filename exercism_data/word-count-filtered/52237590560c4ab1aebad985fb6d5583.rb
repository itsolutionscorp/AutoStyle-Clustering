class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    @words.
      downcase.
      split(/[^A-Za-z0-9]+/).
      group_by{|w| w}.
      reduce({}) do |hash, (k,v)|
        hash[k] = v.length
        hash
      end
  end
end
