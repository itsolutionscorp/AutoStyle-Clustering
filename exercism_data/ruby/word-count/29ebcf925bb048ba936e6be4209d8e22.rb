class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    count = {}

    @sentence.split(/[,\s]/).each do |p|
      p.downcase!
      p.gsub! /[^a-z0-9\s']/, ''

      next unless p.length > 0

      count[p] = count.has_key?(p) ? count[p]+1 : 1
    end

    count
  end
end
