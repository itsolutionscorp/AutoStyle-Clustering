class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    @sentence.downcase.split(/[,\s]/).each_with_object(Hash.new(0)) do |p, count|
      p.gsub! /[^a-z0-9\s']/, ''
      count[p] += 1 if p.length > 0
    end
  end
end
