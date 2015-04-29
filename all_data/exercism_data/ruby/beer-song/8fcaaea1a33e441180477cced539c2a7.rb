class Beer

  def verse(n)
    "#{bottles_human(n).capitalize} of #{subject} on the wall, #{bottles_human(n)} of #{subject}.\n#{action_by_bottles(n)}.\n"
  end

  def subject
    'beer'
  end

  def bottles_human(bottles_num)
    (bottles_num == 0 ? 'no more' : bottles_num.to_s) + ' ' + inflect_bottles(bottles_num)
  end

  def inflect_bottles(bottles_num)
    bottles_num == 1 ? 'bottle' : 'bottles'
  end

  def bottle_to_take(bottles_num)
    bottles_num == 1 ? 'it' : 'one'
  end

  def action_by_bottles(bottles_num)
    if bottles_num > 0
      "Take #{bottle_to_take(bottles_num)} down and pass it around, #{bottles_on_the_wall(bottles_num - 1)}"
    else
      "Go to the store and buy some more, #{bottles_on_the_wall(99)}"
    end
  end

  def bottles_on_the_wall(bottles_num)
    "#{bottles_human(bottles_num)} of beer on the wall"
  end

  def sing(starting_verse, ending_verse = 0)
    starting_verse.downto(ending_verse).map do |bottles_num|
      verse(bottles_num) + "\n"
    end.join
  end

end
