class Phrase
attr_accessor :text
  def initialize(text)
    @text = text
  end

  def word_count
    occurs = {}
    @text.downcase
      .scan(/[\w|']+/)
      .each {|w| occurs[w]  ? occurs[w] += 1 : occurs[w] = 1 }
    occurs
  end
end
