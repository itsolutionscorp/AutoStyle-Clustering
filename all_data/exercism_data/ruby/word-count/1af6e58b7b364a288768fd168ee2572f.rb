class Tokenizer
  def tokens_from(string)
    string.split.
      map    { |token| remove_punctuation(token) }.
      map    { |token| normalize_case(token) }.
      reject { |token| token.empty? }
  end
  
  private
  
  def remove_punctuation(token)
    token.gsub(/[^\w]/, "")
  end
  
  def normalize_case(token)
    token.downcase
  end
end

class Words
  def initialize(sentence)
    @tokens = Tokenizer.new.tokens_from(sentence)
  end
  
  def count
    word_counts(@tokens)
  end
  
  private
  
  def word_counts(words)
    Hash.new(0).tap do |counts|
      words.each { |word| counts[word] += 1 }
    end
  end
end
