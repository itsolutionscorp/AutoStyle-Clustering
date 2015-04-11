class BeerSong
  TEMPLATE =

  def verse(n)
    case
    when plural?(n)
      "#{bottle(n)} of beer on the wall, #{bottle(n)} of beer.\nTake one down and pass it around, #{bottle(n - 1)} of beer on the wall.\n"
    when singular?(n)
      "#{bottle(n)} of beer on the wall, #{bottle(n)} of beer.\nTake it down and pass it around, #{bottle(n - 1)} of beer on the wall.\n"
    else
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    end
  end

  def verses(from ,to)
    (to .. from).to_a.reverse.map do |n|
      verse(n)
    end.join("\n") + "\n"
  end

  def sing
    verses 99, 0
  end

  private

  def plural?(n)
    n >= 2
  end

  def singular?(n)
    n == 1
  end


  def bottle(n)
    case
    when plural?(n)
      "#{n} bottles"
    when singular?(n)
      "#{n} bottle"
    else
      "no more bottles"
    end
  end
end
