class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count()
    words = @phrase.downcase.gsub(/[^A-Za-z0-9,'\s]/,"").gsub(/,/, " ").split()
    word_list = {}
    words.each do |word|
      if word_list[word].nil?
        word_list[word] = 1
      else
        word_list[word] += 1
      end
    end
    word_list
  end
end
