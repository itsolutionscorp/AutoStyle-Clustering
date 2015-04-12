class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    clean_text.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] += 1
    end
  end

  private 

  def clean_text
    @sentence.downcase.gsub(/[^A-Za-z0-9\s]/, '').split
  end
end