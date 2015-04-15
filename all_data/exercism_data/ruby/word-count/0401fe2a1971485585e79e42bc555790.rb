class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    clean = clean(@words)
    clean.split.inject({}) { |occ, word| occ[word] = occ[word].to_i + 1; occ }
  end

  def clean(str)
    str = str.gsub(',', " ")
    str.scan(/[a-zA-Z0-9\' \,]*/).join.downcase
  end

end
