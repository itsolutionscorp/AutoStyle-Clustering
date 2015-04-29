class PhraseAnalyser

  attr_reader :phrase

  def initialize(phrase_input)
    @phrase = phrase_input
  end

  def words
    phrase.downcase.scan(/\w+/) 
  end

  def characters
    phrase.downcase.delete(" ").split("")
  end

  def total_word_count
    words.size
  end

  def total_character_count
    characters.size
  end

  def word_frequency_count
    words.each_with_object(Hash.new(0)) do |word, frequency|
      frequency[word] += 1
    end
  end

  def character_frequency_count
    characters.each_with_object(hash.new(0)) do |character, frequency|
      frequency[character] += 1
    end
  end

end
