class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    clean_phrase.split.each.with_object(Hash.new 0) do |word, answer|
      answer[word] += 1
    end
  end

  private 

  def clean_phrase
    @phrase.downcase.gsub(/[^\w\d]/, ' ')
  end
end
