class Rhyme
  attr_reader :num, :prv

  def initialize number_verse
    @num = number_verse
    @prv = @num-1
  end

  def line_1
    if @num == 0
      "No more bottles of beer on the wall, no more bottles of beer.\n"
    else
      "#{ get_bottles(@num) } of beer on the wall, #{ get_bottles(@num) } of beer.\n"
    end
  end

  def line_2
    case @prv
    when -1
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    when 0
      "Take it down and pass it around, no more bottles of beer on the wall.\n"
    else
      "Take one down and pass it around, #{ get_bottles(@prv) } of beer on the wall.\n"
    end
  end

  def verse
      return line_1 + line_2
  end

  def get_bottles number_verse
    number_verse == 1 ? "#{ number_verse } bottle" : "#{ number_verse } bottles"
  end
end

class BeerSong
  def verse num
    Rhyme.new(num).verse
  end

  def verses start, finish
    (finish..start).to_a.reverse.map { |i| verse(i) + "\n" }.join
  end

  def sing
    verses(99, 0)
  end
end
