class BeerSong
  def sing
    verses(99, 0)
  end

  def verses(first, last)
    (last..first).to_a.reverse.map { |i| verse(i) }.join("\n") + "\n" 
  end

  def verse(n)
    "#{first_sentence(n)}\n#{second_sentence(n)}\n"
  end

  def first_sentence(n)
    "#{bottles(n).capitalize} #{beer} #{wall}, #{bottles(n)} #{beer}."
  end

  def second_sentence(n)
    case 
    when n > 0 
      "Take #{pronoun(n)} down and pass it around, #{bottles(n-1)} #{beer} #{wall}."
    else
      "Go to the store and buy some more, 99 bottles of beer on the wall."
    end
  end

  def bottles(n)
    txt = case 
    when n > 1 
      "#{n} bottles"
    when n == 1 
      "#{n} bottle"
    else
      "no more bottles"
    end
  end

  def pronoun(n)
    case 
    when n == 1
      "it"
    else 
      "one"
    end
  end

  def beer 
    "of beer"
  end

  def wall
    "on the wall"
  end
end
