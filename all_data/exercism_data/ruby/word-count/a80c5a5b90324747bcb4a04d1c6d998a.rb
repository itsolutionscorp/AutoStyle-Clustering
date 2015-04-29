class Phrase
  def initialize(text)
    words = text.scan(/[\w'0-9]+/i)
    @counts = Hash.new(0)
    words.each { |word| @counts[word.downcase]+=1 }
  end

  def word_count
    @counts
  end
end
