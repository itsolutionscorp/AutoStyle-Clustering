class BeerSong
  def initialize
    @count = 0
    @song = ""
  end

  def verse(count)
    @count = count
    pluralize
    return @song
  end

  def verses(start, finish)
    part = ""
    start.downto(finish) {|c| part += verse(c) + "\n"}
    part
  end

  def sing
    verses(99, 0)
  end

  private

  def pluralize
    case @count
    when 2
      @song = "#{@count} bottles of beer on the wall, #{@count} bottles of beer.\nTake one down and pass it around, #{@count-1} bottle of beer on the wall.\n"
    when 1
      @song = "#{@count} bottle of beer on the wall, #{@count} bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    when 0
      @song = "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
      @count = 100
    else
      @song = "#{@count} bottles of beer on the wall, #{@count} bottles of beer.\nTake one down and pass it around, #{@count-1} bottles of beer on the wall.\n"
    end
    @count -= 1
  end
end
