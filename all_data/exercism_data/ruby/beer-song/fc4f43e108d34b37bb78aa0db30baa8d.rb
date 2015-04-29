require 'pry-byebug'
class BeerSong
  def verse(num) 
    @num = num
    if @num == 1
      verse_1
    elsif @num == 2
      verse_2
    elsif @num == 0
      verse_0
    else
      typical_verse(@num)
    end
  end

    def verse_0
      "No more bottles of beer on the wall, no more bottles of beer.\n" \
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    end

    def verse_1
      "1 bottle of beer on the wall, 1 bottle of beer.\n" \
      "Take it down and pass it around, no more bottles of beer on the wall.\n"
    end

    def verse_2
      "2 bottles of beer on the wall, 2 bottles of beer.\n" \
      "Take one down and pass it around, 1 bottle of beer on the wall.\n"
    end

    def typical_verse(num)
     "#{num} bottles of beer on the wall, #{num} bottles of beer.\n" \
     "Take one down and pass it around, #{num - 1} bottles of beer on the wall.\n"
    end

    def verses(num, limit)
      num.downto(limit) { |num| "#{num} bottles of beer on the wall, #{num} bottles of beer.\n" \
     "Take one down and pass it around, #{num - 1} bottles of beer on the wall.\n" }
    end
end
test = BeerSong.new
test.verses(8, 6)
