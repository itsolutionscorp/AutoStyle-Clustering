class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.reject { |word| word.downcase == @word.downcase } \
         .select { |word| canonical(word) == canonical(@word) }
  end

  private

  def canonical(word)
    @canonical_words ||= Hash.new do |h, k|
      h[k] = k.chars.sort
    end

    @canonical_words[word.downcase]
  end
end
