class Beer
  def verse(bottle_count)
    raise only_natural_number_of_beers if bottle_count < 0

    if bottle_count == 0
      is_it_time_for_aa_maybe_not
    elsif bottle_count == 1
      impending_facing_of_twelve_steps
    else
      lots_of_remaining_drunkenness(bottle_count)
    end
  end

  def sing(start_at_verse, end_at_verse = 0, sung = [])
    sung << "#{verse(start_at_verse)}\n"

    if start_at_verse == end_at_verse
      sung.join
    else
      sing(start_at_verse - 1, end_at_verse, sung)
    end
  end

  private
  def impending_facing_of_twelve_steps
    "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
  end

  def is_it_time_for_aa_maybe_not
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def lots_of_remaining_drunkenness(bottle_count)
    next_round = bottle_count - 1
    "#{bottle_count} #{bottle_or_bottles(bottle_count)} of beer on the wall, #{bottle_count} #{bottle_or_bottles(bottle_count)} of beer.\nTake one down and pass it around, #{next_round} #{bottle_or_bottles(next_round)} of beer on the wall.\n"
  end

  def only_natural_number_of_beers
    "Natural numbers of beers on the wall, natural numbers of beer.\nIf you can't comply, well, then I'm gonna cry, natural number of beers on the wall."
  end

  def bottle_or_bottles(bottle_count)
    bottle_count > 1 ? "bottles" : "bottle"
  end
end
