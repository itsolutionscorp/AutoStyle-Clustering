class Phrase
  def initialize phrase
    @words = {}
    phrase.scan(/[\w']+/) do |word|
      word.downcase!
     if @words[word] == nil
       @words[word] = 1
     else
       @words[word] = @words[word] + 1
     end
    end
  end

  def word_count
    @words
  end

end
