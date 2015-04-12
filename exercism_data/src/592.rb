class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    hash = {}
    words = @phrase.split(/[\s,]/)
    words.each do |word|
      word = word.gsub(/[!&@$%^&:.]/, '').downcase
      if hash.has_key?(word)
        hash[word] += 1
      else
        hash[word] = 1 unless word.empty?
      end
    end
    hash
  end
end
