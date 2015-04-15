class BeerSong
  def verse num
    @verse = num
    first_line + second_line
  end

  def verses first, last
    first.downto(last).reduce("") do |result,verse_num|
      result << verse(verse_num) + "\n"
    end
  end

  def sing
    verses 99, 0
  end

  private
    def first_line
      "#{num_bottles.capitalize} of beer on the wall, #{num_bottles} of beer.\n"
    end

    def num_bottles
      case @verse
      when 1
        "1 bottle"
      when 0
        "no more bottles"
      else
        "#{@verse} bottles"
      end
    end

    def second_line
      case @verse
      when 2
        "Take one down and pass it around, " +
        "1 bottle of beer on the wall.\n"
      when 1
        "Take it down and pass it around, " +
        "no more bottles of beer on the wall.\n"
      when 0
        "Go to the store and buy some more, " +
        "99 bottles of beer on the wall.\n"
      else
        "Take one down and pass it around, " +
        "#{@verse - 1} bottles of beer on the wall.\n"
      end
    end
end
