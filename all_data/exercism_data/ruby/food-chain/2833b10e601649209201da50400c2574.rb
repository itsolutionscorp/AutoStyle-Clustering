class FoodChainSong
  OLD_LADY_SONG_VERSES = File.open('old_lady_song.txt').read.split(/\n\n/).map { |v| v + "\n" }

  def verse(number)
    OLD_LADY_SONG_VERSES[number - 1]
  end

  def verses(start, finish)
    (start..finish).map { |n| verse(n) + "\n" }.join
  end

  def sing
    OLD_LADY_SONG_VERSES.join("\n") + "\n"
  end

end
