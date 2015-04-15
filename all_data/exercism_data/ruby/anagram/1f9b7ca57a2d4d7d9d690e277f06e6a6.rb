module Digest
  refine String do
    def digest
      self.chars.sort
    end
  end
end

using Digest

class Anagram
  def initialize(text)
    @source = text.downcase
    @digest = @source.digest
  end

  def match(words)
    words.select do |w|
      word = w.downcase
      word != @source && word.digest == @digest
    end
  end
end
