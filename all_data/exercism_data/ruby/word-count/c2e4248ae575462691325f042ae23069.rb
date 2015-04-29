class Phrase
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    counts = {}
    word_list.each do|word|
      if counts[word].nil?
        counts[word] = 1
      else
        counts[word] += 1
      end
    end
    counts
  end

  def word_list
    phrase.downcase.tr('^A-Za-z0-9', ' ').split
  end
end
