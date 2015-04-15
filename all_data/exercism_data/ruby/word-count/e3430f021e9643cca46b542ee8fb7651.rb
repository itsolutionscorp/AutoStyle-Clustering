class Phrase
  def initialize(word)
    @word = word.downcase
  end

  def word_count
    count = {}
    @word.split(/(?!')\W+/).each do |word|
      if count[word] == nil
        count[word] = 1
      else
        count[word] = count[word] + 1
      end
    end
    count
  end
end
