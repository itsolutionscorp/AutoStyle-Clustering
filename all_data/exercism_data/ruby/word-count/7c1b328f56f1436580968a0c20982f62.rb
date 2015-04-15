class Words
  def initialize(sentence)
    @sentence = sentence
  end
  
  def count
    word_counts(tokens_from(@sentence))
  end
  
  private
  
  def tokens_from(string)
    normalized_case = string.downcase
    no_punctuation = normalized_case.gsub(/[^\w ]/, "")
    no_punctuation.split(" ")
  end
  
  def word_counts(words)
    Hash.new(0).tap do |counts|
      words.each { |word| counts[word] += 1 }
    end
  end
end
