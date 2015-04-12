class Phrase
  def initialize(text)
    @text = text.downcase
  end

  def word_count
    words = {}
    each_word do |word|
      words[word] = count(word)
    end
    words
  end

  private
  def each_word(&block)
    @text.scan(/[\w'-]+/).uniq.each do |word|
      block.call word
    end
  end

  def count(word)
    pattern = Regexp.new("#{word}\\b")
    @text.scan(pattern).length
  end
end
