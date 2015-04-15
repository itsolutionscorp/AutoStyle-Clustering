class Proverb
  def initialize(*words, qualifier: nil)
    @words, @qualifier = words, qualifier
  end

  def to_s
    @words.reduce("") { |adage, word| adage + next_line(word) }
  end

  private

  def next_line(word)
    last_word?(word) ? envoi : verse(word)
  end

  def last_word?(word)
    word == @words.last
  end

  def verse(word)
    "For want of a #{word} the #{get_next(word)} was lost.\n"
  end

  def get_next(word)
    @words[@words.index(word)+1]
  end

  def envoi
    "And all for the want of a#{space}#{@qualifier} #{first_word}."
  end

  def first_word
    @words[0]
  end

  def space
    " " if @qualifier
  end
end
