class Phrase
  IGNORED_PUNCTUATION = /'/
  WORD_SEPARATOR = /[^\w]+/

  def initialize(string)
    @words = string.downcase.gsub(IGNORED_PUNCTUATION, "").split(WORD_SEPARATOR)
  end

  def word_count
    @words.each.with_object(Hash.new(0)) do |word, counts_hash|
      counts_hash[word] += 1
    end
  end
end
