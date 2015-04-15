class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    answer = {}
    words.each do |word|
      answer.has_key?(word) ?  answer[word] += 1 : answer[word] = 1
    end
    answer
  end

  private

  def words
    lowerize.scan(wanted)
  end

  def lowerize
    @sentence.downcase
  end

  def wanted
    %r(\w+)
  end
end
