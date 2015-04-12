class Phrase
  def initialize string
    @string = string.downcase
  end

  def word_count
    @string.split(/\W/).inject(Hash.new(0)) do |hash, word|
      next hash if word.empty?
      hash[word] += 1
      hash
    end
  end
end
