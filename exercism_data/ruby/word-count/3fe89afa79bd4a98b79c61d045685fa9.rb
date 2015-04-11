class Phrase

  attr_reader :storage

  def initialize(phrase)
    scanner = WordScanner.new(phrase)
    words = scanner.split!
    @storage = WordStorage.new(words)
  end

  def word_count
    storage.words
  end

end

class WordScanner

  attr_accessor :phrase

  PUNCTUATION = /(?!,)[[:punct:]\^$]/
  DELIMITER   = ','
  WHITESPACE  = ' '

  def initialize(phrase)
    @phrase = phrase
  end

  def split!
    prepare_phrase
    split_phrase
  end

  private

  def prepare_phrase
    remove_punctuation!
    remove_whitespace!
    set_delimitier!
  end

  def split_phrase
    self.phrase = phrase.split(DELIMITER).delete_if do |entry|
      entry.empty?
    end.map do |word|
      word.downcase
    end
  end

  def remove_punctuation!
    self.phrase = phrase.gsub(PUNCTUATION, '')
  end

  def remove_whitespace!
    self.phrase = phrase.gsub(/\s+/, WHITESPACE)
  end

  def set_delimitier!
    self.phrase = phrase.gsub(/\s+/, DELIMITER)
  end

end

class WordStorage

  WORD_INC_COUNT = 1

  def initialize(words)
    @words = save(words)
  end

  def words
    storage
  end

  private

  def save(words)
    words.each { |word| store(word) }
  end

  def store(word)
    storage[word] = if storage.has_key?(word)
                      storage[word] + WORD_INC_COUNT
                    else
                      WORD_INC_COUNT
                    end
  end

  def storage
    @storage ||= {}
  end

end
