class Phrase 
  attr_accessor :words

  def initialize(words)
    @words = words
    words_sanitation
  end

  def words_sanitation
    words.gsub!(/[,\.!&@:$%^]/, " ")
  end

  def word_count
    count = {}
    words.split(" ").each do |word|
      word = word.downcase
      count[word] ? count[word] += 1 : count[word] = 1
    end
    count
  end
end
