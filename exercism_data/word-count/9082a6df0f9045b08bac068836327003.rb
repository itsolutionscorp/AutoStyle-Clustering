class Words

  attr_reader :words
  def initialize(words)
    @words = words.downcase.gsub(/\W/, ' ').split(' ')
  end

  def count
    words.each_with_object({}) do |word, hash|
      hash[word] = words.count(word)
    end
  end
end     
