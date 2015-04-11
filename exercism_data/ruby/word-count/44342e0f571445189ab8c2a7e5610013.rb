class Phrase
  def initialize(input_phrase)
    @input_phrase = input_phrase
  end

  def word_count
    store = Hash.new
    array_of_words = @input_phrase.split

    parsed_array_of_words = Array.new

    array_of_words.each do |word|
      word.split(",").each do |split_word|
        parsed_array_of_words << split_word.downcase
      end
    end

    parsed_array_of_words.each do |word|
      parsed_word = word.match(/\w+'?\w?/).to_s
      next if parsed_word == ""

      if store[parsed_word] == nil
        store[parsed_word] = 1
      else
        store[parsed_word] += 1
      end
    end

    store
  end
end
