class Phrase
  def initialize(text)
    @phrase = text
  end

  def word_count
    count_dict = {}
    phrase_array = @phrase.split(/[^\w']+/)
    while (word = phrase_array.pop)
      word = word.downcase
      count_dict[word] = count_dict[word].to_i + 1
    end
    count_dict
  end
end
