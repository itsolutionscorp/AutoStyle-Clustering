class Phrase
  def initialize(sentence)
    @sentence = sentence.downcase
  end

  def word_count
    @sentence.split(/[\s,.:!&@\$%^]+/i).each_with_object({}) do |word, count|
      count[word] ||= 0
      count[word] += 1
    end
  end
end
