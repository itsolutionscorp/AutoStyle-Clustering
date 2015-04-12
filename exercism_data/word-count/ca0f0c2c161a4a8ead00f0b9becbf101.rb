class Phrase
  attr_reader :phrase
  attr_accessor :words
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    @words = Hash.new()
    @phrase = strip_all(@phrase)
    @phrase.each_line(' ') { |word| count(word.strip.downcase) }
    @words
  end

  private

  def count word
    if !word.empty?
      if @words[word]
        @words[word] += 1
      else
        @words[word] = 1
      end
    end
  end

  def strip_all word
    word = word.gsub(/,/, ' ')
    word.gsub(/[[:punct:]]|\$|\^/, '')
  end

end
