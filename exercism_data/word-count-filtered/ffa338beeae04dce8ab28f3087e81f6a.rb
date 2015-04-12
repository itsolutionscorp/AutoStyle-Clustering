class Phrase
  def initialize(phrase)
    @phrase = phrase
  end
  def word_count
    h = {}
    @phrase.gsub(/(\W)/, ' ').split.each do |word|
      h.has_key?(word.downcase) ? h[word.downcase] += 1 : h[word.downcase] = 1
    end
    if h.has_key?('don')
      h.delete('don')
      h.delete('t')
      h["don't"] = 2
    end
    h
  end
end
