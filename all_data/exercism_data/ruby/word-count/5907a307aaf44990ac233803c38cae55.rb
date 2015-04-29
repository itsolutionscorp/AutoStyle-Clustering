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
    clean_words.split
  end

  def clean_words
    lowerize.gsub(unwanted, ' ')
  end

  def lowerize
    @sentence.downcase
  end

  def unwanted
    %r([^a-z\d])
  end

  def counted?
    !@answer.empty?
  end
end
