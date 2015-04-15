class Anagram
  def initialize word
    @word = Word.new word
  end

  def match anagrams
    anagrams.select { |anagram| Word.new(anagram).anagram_of?(word) }
  end

  private

  attr_reader :word
end

class Word
  def initialize original
    @original = original.downcase
    @counter  = CharacterCount.new @original
  end

  def anagram_of? other
    other.to_s != to_s && other.character_count == character_count
  end

  def to_s
    original
  end

  def character_count
    counter.count
  end

  private

  attr_reader :original
  attr_reader :counter
end

class CharacterCount
  def initialize word
    @word    = word
    @map     = FrequencyMap.new
    @counted = false
  end

  def count
    unless previously_counted?
      word.each_char { |letter| map.increment(letter) }
      record_count
    end

    map.count
  end

  private

  attr_reader :map
  attr_reader :word

  def previously_counted?
    @counted
  end

  def record_count
    @counted = true
  end
end

class FrequencyMap
  def initialize map = {}
    @map = map
  end

  def increment item
    map[item] ||= 0
    map[item] += 1
  end

  def count
    map.dup
  end

  private

  attr_reader :map
end
