class Phrase
  IGNORED_PUNCTUATION = /[^\w\s]/

  def initialize(string)
    @words = string.downcase.gsub(IGNORED_PUNCTUATION, "").split
  end

  def word_count
    @words.inject(Hash.new(0)) do |counts_hash, word|
      counts_hash[word] += 1
      counts_hash
    end
  end
end
