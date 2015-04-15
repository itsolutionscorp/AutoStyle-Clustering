class Words
  attr_reader :sentence

  def initialize(sentence)
    @sentence = normalize_sentence(sentence)
  end

  def count
    results = Hash.new(0)
    sentence.split(' ').each do |word|
      results[word] += 1
    end
    results
  end

  private

  def normalize_sentence(sentence)
    sentence.gsub(/\W/, ' ').downcase
  end
end
