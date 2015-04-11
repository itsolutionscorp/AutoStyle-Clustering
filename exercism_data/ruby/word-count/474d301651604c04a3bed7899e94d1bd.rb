class Phrase
  attr_reader   :word_count

  def initialize(phrase)
    @word_count = {}
    phrase.split(/[^\w']+/).each do |word|
      word.downcase!
      if @word_count[word]
        @word_count[word] += 1
      else
        @word_count[word] = 1
      end
    end
    @word_count
  end

end
