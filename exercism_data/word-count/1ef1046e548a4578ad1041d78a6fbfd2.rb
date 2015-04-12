class Words

  attr_reader :string_of_words
  def initialize(string_of_words)
    @string_of_words = string_of_words.downcase.gsub(/\W/, ' ').split(' ')
  end

  def count
    string_of_words.each_with_object({}) do |word, hash|
      hash[word] = string_of_words.count(word)
    end
  end
end     
