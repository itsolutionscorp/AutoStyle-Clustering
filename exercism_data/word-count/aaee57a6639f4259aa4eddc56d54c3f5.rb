class Phrase
  def initialize(input)
    @statement = input
  end

  def array_of_words
    words = @statement.split(/\W/)
    words.delete('')
    words
  end

  def word_count
    occurence_list = {}
    count = 1
    array_of_words.each do |word|
      word = word.downcase
      if occurence_list.has_key?(word)
        occurence_list[word] = occurence_list[word] + 1
      else      
        occurence_list[word] = count
      end
    end
    occurence_list
  end

end
