class Beer

  def sing(starting_beer_count, ending_beer_count = 0)
    starting_beer_count.downto(ending_beer_count).inject("") do |song, beer_count|
      song << verse(beer_count)
      song << "\n"
    end
  end

  def verse(bottles)
    if bottles > 0
      "#{pluralize_bottle(bottles)} of beer on the wall, #{pluralize_bottle(bottles)} of beer.\nTake #{bottle_noun(bottles)} down and pass it around, #{pluralize_bottle(bottles - 1)} of beer on the wall.\n"
    else
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    end
  end

  def pluralize_bottle(count)
    if count > 1
      "#{count} bottles"
    elsif count == 1
      "#{count} bottle"
    else
      'no more bottles'
    end
  end

  def bottle_noun(count)
    count > 1 ? 'one' : 'it'
  end
end
