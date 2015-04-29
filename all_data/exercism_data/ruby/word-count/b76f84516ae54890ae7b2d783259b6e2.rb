class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    word_array = @words.split(' ')
    # eliminate all non-alpha chars
    # eliminate empty elements, except '
    # downcase
  end
end


# def count_words(phrase)
#     words = phrase.downcase.scan(/[\w\']+/)
#     words.each_with_object(Hash.new 0) do |word, count|
#       count[word] += 1
#     end
#   end
