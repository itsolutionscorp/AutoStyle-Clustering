class Proverb

  attr_accessor :words

  def initialize(*words, qualifier: '')
    @words = words
    @qualifier = qualifier
  end

  def to_s
    proverb = generate_proverb
    add_last_verse(proverb, words.first)
  end

  private

  def generate_proverb
    words.each_cons(2).reduce("") do |memo, (prev_word, next_word)|
      memo += generate_verse(prev_word, next_word)
    end
  end

  def generate_verse(word1, word2)
    "For want of a #{word1} the #{word2} was lost.\n"
  end

  def add_last_verse(proverb, word)
    last_verse = "And all for the want of a"
    if @qualifier.empty?
      proverb + "#{last_verse} #{word}."
    else
      proverb + "#{last_verse} #{@qualifier} #{word}."
    end
  end
end
