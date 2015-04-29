class Phrase
  WORD_CHARACTERS = /\w+/

  def initialize(input)
    @words = find_words(input.downcase)
  end

  def word_count
    @words.each_with_object(new_counters) do |word, counters|
      counters[word] += 1
    end
  end

  private
  def find_words(input)
    input.scan(WORD_CHARACTERS)
  end

  def new_counters
    Hash.new(0)
  end
end
