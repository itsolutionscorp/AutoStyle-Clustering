class BeerSong
  def verse(i)
    case
    when i > 2
      "#{i} bottles of beer on the wall, #{i} bottles of beer.\n"\
      "Take one down and pass it around, #{i-1} bottles of beer on the wall.\n"
    when i == 2
      "#{i} bottles of beer on the wall, #{i} bottles of beer.\n"\
      "Take one down and pass it around, #{i-1} bottle of beer on the wall.\n"
    when i == 1
      "#{i} bottle of beer on the wall, #{i} bottle of beer.\n"\
      "Take it down and pass it around, no more bottles of beer on the wall.\n"
    when i == 0
      "No more bottles of beer on the wall, no more bottles of beer.\n"\
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    end
  end

  def verses(i, j)
    raise ArgumentError if j > i

    (j..i).to_a
          .reverse
          .each_with_object("") { |n, song| song << verse(n) << "\n" }
  end

  def sing
    verses(99, 0)
  end
end
