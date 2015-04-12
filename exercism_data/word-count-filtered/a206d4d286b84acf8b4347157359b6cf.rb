class Phrase
  def initialize(phrase)
    @words = phrase.downcase.tr("!&@$%^&:.", "").split(/[,\s]+/)
  end

  def word_count
    @words.reduce({}) do |hash, word|
      hash[word] ? hash[word] += 1 : hash[word] = 1
      hash
    end
  end
end
