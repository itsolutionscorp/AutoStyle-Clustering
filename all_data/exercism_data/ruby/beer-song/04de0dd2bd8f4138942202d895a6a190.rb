class Beer


  def verse(verse_number)
    get_verse(verse_number)
  end

  def sing(verse_start, verse_end = 0)
    lyrics = ''

    verse_start.downto(verse_end) do
      lyrics += get_verse(verse_start) + "\n"
      verse_start = verse_start - 1
    end

    lyrics
  end

  def get_verse(verse_number)

    case verse_number
      when 2
        <<EOF
#{verse_number} bottles of beer on the wall, #{verse_number} bottles of beer.
Take one down and pass it around, #{verse_number-1} bottle of beer on the wall.
EOF
      when 1
        <<EOF
1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.
EOF
      when 0
        <<EOF
No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
EOF
      else
        <<EOF
#{verse_number} bottles of beer on the wall, #{verse_number} bottles of beer.
Take one down and pass it around, #{verse_number-1} bottles of beer on the wall.
EOF
    end

  end

end
