class Phrase
  def initialize(phrase)
    @words = phrase_to_array_of_words phrase
  end

  def word_count
    counter = {}
    @words.each do |word|
      word = matched_word word
      next if word.nil?
      counter[word] = counter[word].nil? ? 1 : counter[word] + 1
    end
    counter
  end

  private

  def matched_word word
    word.match(/\w+/).nil? ? nil : $~[0]
  end

  def phrase_to_array_of_words phrase
    phrase.to_s.downcase.split(/\W/)
  end
end
