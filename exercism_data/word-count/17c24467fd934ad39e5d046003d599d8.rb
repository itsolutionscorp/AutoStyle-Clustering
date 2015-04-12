class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_array = prep_word_array
    word_count = Hash.new

    word_array.each do |word|
      word_count.has_key?(word) ? word_count[word] += 1 : word_count[word] = 1
    end
    word_count
  end

  def prep_word_array
    word_array = @phrase.gsub(/\W/, " ").split

    word_array.each do |word|
      word.downcase!
      if !word[/\w/]
        word_array.delete(word)
      end
    end
  end

end
