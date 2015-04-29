class BeerSong

  def initialize
    @song = build_song
  end

  def verse(num)
    @song[num]
  end

  def verses(num, num2)
    @song[num2..num].reverse.join("\n") << "\n"
  end

  def sing
    @song.reverse.join("\n") << "\n"
  end

  def build_song
    song = []
    99.times do |i|
      if i == 0
        song << "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
      elsif i == 1
        song << "#{i} bottle of beer on the wall, #{i} bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
      elsif i == 2
        song << "#{i} bottles of beer on the wall, #{i} bottles of beer.\nTake one down and pass it around, #{i-1} bottle of beer on the wall.\n"
      else
        song << "#{i} bottles of beer on the wall, #{i} bottles of beer.\nTake one down and pass it around, #{i-1} bottles of beer on the wall.\n"
      end
    end
    song
  end

end
