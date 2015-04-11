class Phrase

  def initialize word
    @word = word
  end

  def word_count
    dictionary = Hash.new {|h, k| h[k] = 0}
    split(clean_word @word).reduce dictionary do |wc, w|
      wc[w] += 1
      wc
    end
  end

  private

  def clean_word word
    word.gsub! /[^\w\s,']+/, ''
    word.downcase!
    word
  end

  def split word
    word.gsub(/,/, ' ').split ' '
  end
end
