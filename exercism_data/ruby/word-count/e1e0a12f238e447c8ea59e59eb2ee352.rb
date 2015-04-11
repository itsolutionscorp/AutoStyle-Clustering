class Phrase
  def initialize words
    @words = words
  end

  def word_count
    word_list = get_word_list
    word_list.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] = counts[word] + 1
    end
  end

  private
  def get_word_list
    @words.downcase.scan /\w+/
  end
end
