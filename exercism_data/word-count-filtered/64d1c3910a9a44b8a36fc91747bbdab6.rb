class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count()
    words = @phrase.downcase.split(/[^A-Za-z0-9']/).reject(&:empty?)
    word_list = {}
    words.each do |word|
        word_list[word] ||= 0
        word_list[word] += 1
    end
    word_list
  end
end
