class Phrase
  def initialize sentence
    @sentence = sentence
  end

  def word_count
    @sentence.downcase.gsub(match_punctuation, '').reduce(Hash.new(0)) do |count_hash, word|
      count_hash[word] += 1
      count_hash
    end
  end

  private

  def match_punctuation
    /[^a-z0-9,'\s]/
  end
end
