class Phrase
  attr_reader :data

  def initialize(input)
   words = input.downcase.scan(/[\w']+/)
   @data = Hash.new(0)
    words.each do |word|
      data[word] += 1
    end
  end


  def word_count
    data
  end


end
