class Phrase
  DELIMITERS = /[^a-zA-Z0-9_']+/

  def initialize(text)
    @text = text
  end

  def word_count
    words = @text.split(DELIMITERS)
    count = {}
    words.each do |w|
      w.downcase!
      count[w] ? count[w] += 1 : count[w] = 1
    end
    return count
  end
end
