class Proverb

  attr_accessor :words

  def initialize(*words, qualifier: '')
    @words = words
    @qualifier = qualifier
  end

  def to_s
    proverb = words.each_cons(2).inject("") do |memo, words|
      prev_word = words.first
      next_word = words.last

      memo += generate_verse(prev_word, next_word)
    end
    proverb += last_verse(words.first)
  end

  private
  def generate_verse(word1, word2)
    "For want of a #{word1} the #{word2} was lost.\n"
  end

  def last_verse(word)
    if @qualifier.empty?
      "And all for the want of a #{word}."
    else
      "And all for the want of a #{@qualifier} #{word}."
    end
  end
end
