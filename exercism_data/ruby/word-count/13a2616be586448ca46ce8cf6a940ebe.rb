# encoding: utf-8

class Words
  def initialize(sentence)
    @words = extract_words(sentence)
  end

  def count
    @words.each_with_object(Hash.new(0)) do |word, result|
      result[word] += 1
    end
  end

  private

  def extract_words(string)
    string.downcase.split(/\W+/)
  end
end
