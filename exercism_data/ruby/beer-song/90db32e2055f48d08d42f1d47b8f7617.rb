class Beer
  def sing(high, low=0)
    high.downto(low).map { |n| verse n }.join("\n") + "\n"
  end

  def verse(n)
    challenge(n) + "\n" + response(n) + "\n"
  end

  private
  def challenge(bottles)
    case bottles
    when 1
      "1 bottle of beer on the wall, 1 bottle of beer."
    when 0
      "No more bottles of beer on the wall, no more bottles of beer."
    else
      "#{bottles} bottles of beer on the wall, #{bottles} bottles of beer."
    end
  end

  def response(bottles)
    case bottles
    when 2
      "Take one down and pass it around, 1 bottle of beer on the wall."
    when 1
      "Take it down and pass it around, no more bottles of beer on the wall."
    when 0
      "Go to the store and buy some more, 99 bottles of beer on the wall."
    else
      "Take one down and pass it around, #{bottles - 1} bottles of beer on the wall."
    end
  end
end
