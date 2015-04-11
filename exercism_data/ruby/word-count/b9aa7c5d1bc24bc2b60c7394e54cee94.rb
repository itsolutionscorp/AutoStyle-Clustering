class Phrase
  
  attr_reader :phraseIn

  def initialize(phraseIn)
    @phraseIn = phraseIn.downcase.scan(/\w+'\w|\w+/)
  end

  def word_count
    phraseHash = Hash.new(0)
    phraseIn.each {|word| phraseHash[word] += 1}
    phraseHash
  end

end
