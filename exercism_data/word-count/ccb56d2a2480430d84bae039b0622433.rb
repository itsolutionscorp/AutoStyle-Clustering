class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    count = {}
    phrase = @phrase.downcase.gsub(/[^a-zA-Z0-9']/, ' ')
    phrase.split.each do |word|
      if count.include? word
        count[word] += 1
      else
        count[word] = 1
      end
    end
    count
  end
end
