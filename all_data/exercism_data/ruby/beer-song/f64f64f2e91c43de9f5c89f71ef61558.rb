class Beer
  def initialize
    @bottles = []
  end

  def verse(which)
    which = Bottle.new(which) unless @bottles.include?(which)
    which.lyricize
  end

  def sing(start_count, end_count = 0)
    start_count.downto(end_count) { |which| @bottles << Bottle.new(which) }
    @bottles.map { |bottle| "#{verse(bottle)}\n" }.join
  end
end

class Bottle
  attr_accessor :count

  def initialize(which)
    @count = which
  end

  def lyricize
    Lyric.new(self).create_lyric
  end
end

module LyricParts
  FIRST      = [' of beer on the wall, ', " of beer.\n"]
  SECOND     = ['Take ', ' down and pass it around, ',  " of beer on the wall.\n"]
  WORD_FORMS = ['no more bottles', '1 bottle']
  PRONOUNS   = %w(no_pronoun it one)

  def word_form
    WORD_FORMS[@bottle.count] || "#{@bottle.count} bottles"
  end

  def pronoun
    PRONOUNS[@bottle.count] || PRONOUNS[2]
  end
end

class Lyric
  include LyricParts

  def initialize(bottle)
    @bottle = bottle
  end

  def create_lyric
    [first_part, last_part].join
  end

  private

  def first_part
    [word_form, LyricParts::FIRST[0],
     word_form, LyricParts::FIRST[1]].join.capitalize
  end

  def last_part
    if pronoun != 'no_pronoun'
      part = [LyricParts::SECOND[0], pronoun, LyricParts::SECOND[1]]
      @bottle.count -= 1
    else
      part = 'Go to the store and buy some more, '
      @bottle.count = 99
    end

    part = [part, word_form, LyricParts::SECOND[2]].join.capitalize
  end
end
