class Phrase

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    counts = Hash.new { |hash, key| hash[key] = 0 }
    @sentence.downcase.scan(/\w+/) do |word|
      counts[word] += 1
    end
    counts
  end
end
