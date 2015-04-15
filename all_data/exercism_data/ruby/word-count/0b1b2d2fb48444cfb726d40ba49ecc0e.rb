class Phrase
  def initialize(sentence)
    @sentence = sentence
    @counts = Hash.new(0)

    count_words
  end

  def word_count
    @counts
  end

private
  def count_words
    scan_words.each do |word|
      @counts[word] += 1
    end
  end

  def scan_words
    @sentence.scan(/\b(?:\w+|\d+)\b/).each { |word| word.downcase! }
  end
end
