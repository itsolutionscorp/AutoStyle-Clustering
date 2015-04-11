class Phrase
  def initialize(sentence)
    @words = parse_sentence(sentence)
  end

  def word_count
    results = {}
    @words.each do |word|
      results[word] = result_count(results, word)
    end
    results
  end

  private

  def parse_sentence(sentence)
    split = sentence.downcase.split(" ")
    word_array = (split.count == 1) ? sentence.split(",") : split
    word_array.map!{ |s| s.gsub(/[^a-zA-Z\d]/,'') }
    word_array.reject!(&:empty?)
    word_array
  end

  def result_count(results, word)
    results.has_key?(word) ? results[word] + 1 : 1
  end
end
