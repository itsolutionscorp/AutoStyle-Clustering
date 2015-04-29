class Phrase 

  attr_reader :words

  def initialize(words)
    @words = words
  end

  def word_count
    count = Hash.new
    words.downcase.gsub(/\W/, " ").split(" ").each do |word|
      count[word] = 0 unless count.has_key?(word)
      count[word] += 1
    end
    count
  end

end
