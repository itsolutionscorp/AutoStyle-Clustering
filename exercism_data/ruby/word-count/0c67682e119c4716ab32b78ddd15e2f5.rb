class Phrase

  def initialize(sentence)
    @sentence = sanitize_sentence(sentence)
  end

  def word_count
    counts = Hash.new(0)
    @sentence.scan(/\w+/) do |word|
      counts[word] += 1
    end
    counts
  end

  private 

  def sanitize_sentence(sentence)
    sentence.downcase
  end
end
