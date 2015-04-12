class Phrase
  def initialize(input)
    @statement = input
  end

  def array_of_words
    words = []
    @statement.scan(/\w+/) { |w| words << w.downcase }
    words
  end

  def word_count
    occurence_list = Hash.new { |hash, key| hash[key] = 1 }
    array_of_words.each do |word|
      if occurence_list.has_key?(word)
        occurence_list[word] = occurence_list[word] + 1
      else
        occurence_list[word]
      end
    end
    occurence_list
  end

end
