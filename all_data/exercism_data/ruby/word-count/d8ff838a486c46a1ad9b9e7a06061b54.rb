class Phrase
  attr_reader :sentence

  def initialize(sentence)
    @words = parse_sentence(sentence)
  end

  def word_count
    results = {}
    @words.each do |w|
      results[w] = result_count(results, w)
    end
    results
  end

  private

  def parse_sentence(sentence)
    split = sentence.split(" ")
    wa = (split.count == 1) ? sentence.split(",") : split
    wa.map!{ |s| s.downcase.gsub(/[^a-zA-Z\d]/,'') }
    wa.reject!(&:empty?)
    wa
  end

  def result_count(results, word)
    results.has_key?(word) ? results[word] + 1 : 1
  end
end
