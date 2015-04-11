class Phrase
  attr_reader :sentence

  def initialize(sentence)
    @sentence  = sentence
  end

  def word_count
    word_array= []
    count = Hash.new(0)
    sentence.downcase.gsub(/[[:punct:]]/, ' ').split(' ').each {|word| word_array << word}
      word_array.each { |w| count[w] +=1}
      return count
    end
end
