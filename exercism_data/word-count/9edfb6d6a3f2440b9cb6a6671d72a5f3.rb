class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    count = Hash.new(0)
    @sentence.split(/\W+/).each do |word|
      count[word.downcase] +=1
    end
    count
  end
end
