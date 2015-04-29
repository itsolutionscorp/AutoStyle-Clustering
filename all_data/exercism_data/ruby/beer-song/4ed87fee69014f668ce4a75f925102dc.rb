class BeerSong
  def verse(num_on_wall, remaining = num_on_wall)
    phrase1 = "#{bottle_number(num_on_wall).capitalize} #{bottles_plural?(num_on_wall)} of beer on the wall, #{bottle_number(num_on_wall)} #{bottles_plural?(num_on_wall)} of beer.\n"

    num_on_wall -= 1
    if num_on_wall == -1
      phrase2 = "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      phrase2 = "Take #{pronoun(num_on_wall)} down and pass it around, #{bottle_number(num_on_wall)} #{bottles_plural?(num_on_wall)} of beer on the wall.\n"
    end

    return phrase1 + phrase2
  end

  def verses(num_on_wall, remaining)
    return "" if remaining > num_on_wall

    song = verse(num_on_wall) + "\n"
    song += verses(num_on_wall - 1, remaining)
    return song
  end

  def sing
    verses(99, 0)
  end

  private

  def bottles_plural?(num)
    num == 1 ? correctly_pluralized_bottle = "bottle" : correctly_pluralized_bottle = "bottles"
    return correctly_pluralized_bottle
  end

  def bottle_number(num)
    num == 0 ? how_many_bottles = "no more" : how_many_bottles = num.to_s
    return how_many_bottles
  end

  def pronoun(num)
    num == 0 ? correct_pronoun = "it" : correct_pronoun = "one"
    return correct_pronoun
  end

end
