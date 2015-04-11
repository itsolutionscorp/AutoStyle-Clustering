class BeerSong
  attr_accessor :line

  def verse(line)
    @line = line
    start_verse + middle_verse + final_verse
  end

  def verses(start, ending = 0)
    song = ""

    start.downto(ending) do |line|
      song << verse(line) + "\n"
    end

    song
  end

  private

  def start_verse
    "#{bottles.capitalize} of beer on the wall, #{bottles} of beer.\n"
  end

  def middle_verse
    if line == 0
      "Go to the store and buy some more, "
    else
      "Take #{pluralizer} down and pass it around, "
    end
  end

  def final_verse
    "#{bottles(next_bottle)} of beer on the wall.\n"
  end

  def pluralizer
    line == 1 ? "it" : "one"
  end

  def bottles(line = @line)
    case line
    when 0
      "no more bottles"
    when 1
      "#{line} bottle"
    else
      "#{line} bottles"
    end
  end

  def next_bottle
    line > 0 ? line - 1 : 99
  end
end
