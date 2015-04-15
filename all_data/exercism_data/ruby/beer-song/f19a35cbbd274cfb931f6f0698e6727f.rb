class BeerSong
  def verse(v)
    verse_line1 = "#{v == 0 ? 'No more' : v} bottle#{v == 1 ? '' : 's'} of beer on the wall, #{v == 0 ? 'no more' : v} bottle#{v == 1 ? '' : 's'} of beer.\n"
    verse_line2 = "Take #{v == 1 ? 'it' : 'one'} down and pass it around, #{v == 1 ? 'no more' : v - 1} bottle#{v == 2 ? '' : 's'} of beer on the wall.\n"
    verse_line_none = "Go to the store and buy some more, 99 bottles of beer on the wall.\n"

    verse_line1 + (v == 0 ? verse_line_none : verse_line2)
  end

  def verses(verse_start, verse_end)
    verses_output = ''
    (verse_end..verse_start).to_a.reverse.each { |v| verses_output << (verse(v) + "\n") }

    verses_output
  end

  def sing
    verses(99, 0)
  end
end
