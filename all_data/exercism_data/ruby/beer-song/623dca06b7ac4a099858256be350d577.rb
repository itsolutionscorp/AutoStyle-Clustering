class Beer
  def verse which_verse
    verse = Verse.new which_verse
    verse.play
  end

  def sing from, til = 0
    from.downto(til).inject("") { |song, which_verse| song + verse(which_verse) + "\n" }
  end
end

class Verse
  def initialize verse
    @verse = verse
  end

  def play
    verse.zero? ? final : default
  end

  private
  attr_reader :verse

  def default
    "#{bottles} of beer on the wall, #{bottles} of beer.\nTake #{pluralizer} down and pass it around, #{bottles next_verse} of beer on the wall.\n"
  end

  def final
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def bottles num = verse
    case num
    when 0 then "no more bottles"
    when 1 then "#{num} bottle"
    else "#{num} bottles"
    end
  end

  def pluralizer
    verse == 1 ? "it" : "one"
  end

  def next_verse
    verse - 1
  end
end
