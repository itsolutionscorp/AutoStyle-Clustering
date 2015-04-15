class Phrase
  def initialize phrase
    @list      = WordList.new phrase
    @frequency = WordFrequency.new
    @counted   = false
  end

  def word_count
    unless previously_counted?
      list.each { |word| frequency.increment_for_word(word) }
      record_count
    end

    frequency.count
  end

  private

  attr_reader :list
  attr_reader :frequency

  def previously_counted?
    @counted
  end

  def record_count
    @counted = true
  end
end

class WordList
  def initialize phrase
    @phrase = phrase
  end

  def each &block
    phrase.downcase.scan(/\w+/).each &block
  end

  private

  attr_reader :phrase
end

class WordFrequency
  def initialize
    @map = {}
  end

  def increment_for_word word
    map[word] ||= 0
    map[word] += 1
  end

  def count
    @map.dup
  end

  private

  attr_reader :map
end
