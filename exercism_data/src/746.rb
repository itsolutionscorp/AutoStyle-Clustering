class Phrase
  def initialize(sentence)
    @word_array = sentence.gsub(/[^\w[\s]''']/, ' ').split(' ')
    @word_count = Hash.new(0)
    @word_array.each do |word|
      @word_count[word.downcase] += 1
    end
  end

  def word_count
    @word_count
  end
end
