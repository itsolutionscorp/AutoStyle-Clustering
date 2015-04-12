class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = {}
    each_word do |word|
      count[word] = 0 if count[word].nil?
      count[word] = count[word] + 1
    end
    count
  end

  private

  def each_word
    split_phrase_by_space_or_commas.each do |raw_word|
      parsed_word = extract_grammatically_correct_word(raw_word)
      yield(parsed_word.downcase) if parsed_word
    end
  end

  def split_phrase_by_space_or_commas
    @phrase.split(/\s|,/)
  end

  def extract_grammatically_correct_word(word)
    word[/\w+([[:punct:]]?\w+)?/]
  end
end
