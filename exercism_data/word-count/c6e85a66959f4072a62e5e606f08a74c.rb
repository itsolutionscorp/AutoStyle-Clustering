class Phrase
  def initialize(sentence)
    @sentence = sentence
  end
  
  def word_count
    words = @sentence.split /[\ \,]/
    count = {}
    for word in words
      w = word.downcase.gsub /\W/, ''
      count[w] ||= 0
      count[w] += 1
    end
    count.reject { |w| w.empty? }
  end
end
