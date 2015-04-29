class Beer

  def verse(n)
    "#{bottles_of_subject_on_the_wall(n).capitalize}, #{bottles_num(n)} of #{subject}.\n#{take_one_or_go_to_the_store(n)}, #{bottles_of_subject_on_the_wall(n - 1)}.\n"
  end

  def sing(starting_verse, ending_verse = 0)
    starting_verse.downto(ending_verse).map do |n|
      verse(n) + "\n"
    end.join
  end

  private

  def subject
    'beer'
  end

  def bottles_num(n)
    (n == 0 ? 'no more' : n.to_s) + ' ' + inflect_bottles(n)
  end

  def inflect_bottles(n)
    n == 1 ? 'bottle' : 'bottles'
  end

  def bottle_to_take(n)
    n == 1 ? 'it' : 'one'
  end

  def take_one_or_go_to_the_store(n)
    if n > 0
      "Take #{bottle_to_take(n)} down and pass it around"
    else
      "Go to the store and buy some more"
    end
  end

  def bottles_of_subject_on_the_wall(bottles_num)
    bottles_num = 99 if bottles_num < 0
    "#{bottles_num(bottles_num)} of #{subject} on the wall"
  end

end
