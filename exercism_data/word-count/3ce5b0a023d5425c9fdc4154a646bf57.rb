require 'pry'

class Phrase
  def initialize(string)
    @words = string.scan(/\w+/)
  end

  def word_count
    @words.each_with_object(CaseInsensitiveHash.new(0)) do |word, word_count|
      word_count[word] += 1
    end
  end
end

class CaseInsensitiveHash < Hash
  def [](key)
    super(key.downcase)
  end

  def []=(key, value)
    super(key.downcase, value)
  end
end
