class Phrase

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    counts = {}
    @sentence.downcase.scan(/\w+/) do |word|
      unless counts.key? word
        counts[word] = 0
      end
      counts[word] += 1
    end
    counts
  end
end
