class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    result = Hash.new
    words = @phrase.downcase.split(/[\W]+/)
    words.each do |word|
      if !result.has_key?(word)
        result[word] = words.count(word)
      end
    end
    result
  end
end
