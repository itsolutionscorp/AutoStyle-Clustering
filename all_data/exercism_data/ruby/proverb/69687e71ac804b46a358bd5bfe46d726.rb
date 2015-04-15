class Proverb
  def initialize(*words, qualifier: nil)
    @words, @qualifier = words, qualifier
  end

  def to_s
    @words.reduce("") { |adage, word| adage + next_line_with(word) }
  end

  private

  def next_line_with(word)
    last_index?(word,@words) ? envoi : verse(word, next_word(word))
  end

  def last_index?(word, words)
    words.index(word) == words.length-1
  end

  def next_word(word)
    @words[@words.index(word)+1]
  end

  def max_index
    @words.length
  end

  def verse(wanted_item, lost_item)
    "For want of a #{wanted_item} the #{lost_item} was lost.\n"
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
