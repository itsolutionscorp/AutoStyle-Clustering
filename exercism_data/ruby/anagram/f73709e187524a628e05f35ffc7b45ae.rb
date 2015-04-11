class Anagram
  def initialize(query)
    @query = query
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end

  private
  attr_reader :query
  
  def normalized_query
    @normalized_query ||= normalize(query)
  end

  def anagram?(word)
    normalized_query.eql?(normalize(word))
  end

  def normalize(word)
    word.downcase.chars.sort
  end
end
