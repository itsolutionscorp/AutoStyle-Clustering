class Phrase

  def initialize (phrase)
    @phrase=phrase
  end

  def word_count
    wc={}
    # The smartest guy I know once related a story to me.
    # A young programmer had a text problem to solve. He
    # used regular expressions to solve it. Now he has two
    # problems.
    @phrase.split(/[\W&&[^']]+/).each do |word|
      word.downcase!
      wc[word] ? wc[word]+=1: wc[word]=1
    end
    wc
  end

end
