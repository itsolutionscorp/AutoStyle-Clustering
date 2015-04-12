require 'strscan'

class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    init_word_count
    while has_more_words? do
      update_count(next_word)
      skip_punctuation 
    end
    @word_count
  end

  private
  def next_word
    word = @scan.scan(/\w+/)
  end

  def normalize_word(word)
    word.downcase
  end

  def update_count(word)
    @word_count[normalize_word(word)] += 1
  end

  def init_word_count
    @word_count = Hash.new { |hash,key|  hash[key] = 0 }
    @scan = StringScanner.new(@input)
  end

  def has_more_words? 
    !@scan.eos?
  end

  def skip_punctuation
    @scan.skip(/\W+/)
  end
end
