class Phrase
  def initialize(sentence)
    @sentence = sentence
    @answer = {}
  end

  def word_count
    words.each do |word|
      @answer.has_key?(word) ?  @answer[word] += 1 : @answer[word] = 1
    end unless counted?
    @answer
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

  def counted?
    @answer.any?
  end
end
