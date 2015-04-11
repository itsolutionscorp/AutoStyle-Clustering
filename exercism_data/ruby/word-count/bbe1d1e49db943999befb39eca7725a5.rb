class Phrase

  def initialize(phrase)
    @phrase = phrase
    @phrase_array = Array.new
  end

  def word_count
    create_word_array
    word_count_hash = Hash.new
    @phrase_array.each do |word|
      if word_count_hash.has_key?(word)
        word_count_hash[word] += 1
      else
        word_count_hash[word] = 1
      end
    end
    word_count_hash
  end

  def create_word_array
    @phrase_array = @phrase.gsub(/\W/, " ").split
    @phrase_array.each do |word|
      word.downcase!
      if !word[/\w/]
        @phrase_array.delete(word)
      end
    end
  end

end
