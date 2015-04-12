class Phrase
  attr_accessor :new

  def initialize(new ="")
    @new = new
  end

  def word_count
    newWord = @new.downcase.gsub(/[^a-z0-9' ]/, ' ')
    tab = newWord.split
    tabOfWords = []
    countWords = {}
    0.upto(tab.size - 1) do |element|
      if !tabOfWords.include?(tab[element])
        tabOfWords << tab[element]
      end
    end

    for word in tabOfWords
      num = tab.count(word)
      countWords[word] = num
    end
    countWords
  end

end
