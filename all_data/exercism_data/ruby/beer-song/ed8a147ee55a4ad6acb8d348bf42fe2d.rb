class BeerSong
  def verse stanza
    case stanza
    when 2
      return "2 bottles of beer on the wall, 2 bottles of beer.\n" +
        "Take one down and pass it around, 1 bottle of beer on the wall.\n";
    when 1
      return "1 bottle of beer on the wall, 1 bottle of beer.\n" +
        "Take it down and pass it around, no more bottles of beer on the wall.\n";
    when 0
      return "No more bottles of beer on the wall, no more bottles of beer.\n" +
        "Go to the store and buy some more, 99 bottles of beer on the wall.\n";
    else
      return "#{stanza} bottles of beer on the wall, #{stanza} bottles of beer.\n" +
        "Take one down and pass it around, #{stanza - 1} bottles of beer on the wall.\n";
    end
  end

  def verses first, last
    first.downto(last)
      .reduce(Array.new) do |verses, stanza|
      verses << verse(stanza)
    end.join("\n") + "\n"
  end

  def sing
    verses 99, 0
  end
end
