class Phrase
  def initialize(text)
    @phrase = text
  end

  def word_count
    count_dict = Hash.new(0)
    phrase_array = @phrase.split(/[^\w']+/)
    phrase_array.each do |word|
      word.downcase!
      count_dict[word] += 1
    end
    count_dict
  end
end
