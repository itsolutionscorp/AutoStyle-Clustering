class Phrase
  @phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    #replacing all ignored characters with spaces
    words = @phrase.downcase.gsub(/[^a-z0-9'\s]/i, ' ').split(" ")
    list = Hash.new()

    words.map{
      |word|
      if list[word]==nil
        list[word] = 1
      else
        list[word] += 1
      end
    }

    return list
  end
end
