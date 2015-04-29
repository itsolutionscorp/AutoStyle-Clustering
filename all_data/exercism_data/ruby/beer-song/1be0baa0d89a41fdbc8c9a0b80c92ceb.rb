class BeerSong
  def verse(nb)
    part1 = nb_bottles(nb).capitalize
    part2 = nb_bottles nb, false
    part3 = beer_action nb
    part4 = nb_bottles next_nb nb
    "#{part1}, #{part2}.\n#{part3}, #{part4}.\n"
  end

  def verses(start_nb, last_nb)
    verse_list(start_nb, last_nb).map { |nb| verse nb }.join("\n") + "\n"
  end

  def sing
    verses 99, 0
  end

  private

  def verse_list(start, stop)
    (stop..start).to_a.reverse
  end

  def beer_action(nb)
    nb_str = (nb == 1 ? 'it' : 'one')
    act1 = "Take #{nb_str} down and pass it around"
    act2 = 'Go to the store and buy some more'
    nb > 0 ? act1 : act2
  end

  def nb_bottles(nb, add_location = true)
    str = case nb
          when 0
            'no more bottles'
          when 1
            '1 bottle'
          else
            "#{nb} bottles"
          end + ' of beer'
    add_location ? "#{str} on the wall" : str
  end

  def next_nb(nb)
    (nb - 1) % 100
  end
end
