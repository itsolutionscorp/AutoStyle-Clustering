class BeerSong
  def sing
    verses(99, 0)
  end

  def verses(starting, ending)
    starting.downto(ending).reduce('') do |song, num_bottles|
      song << "#{verse(num_bottles)}\n"
    end
  end

  def verse(num_bottles)
    "#{bottle_amount(num_bottles).capitalize} of beer on the wall, " \
      "#{bottle_amount(num_bottles)} of beer.\n#{action(num_bottles)}, " \
      "#{bottle_amount(num_bottles - 1)} of beer on the wall.\n"
  end

  private

  def bottle_amount(num_bottles)
    num_bottles_str = case num_bottles
                      when 0 then 'no more'
                      when -1 then '99'
                      else
                        "#{num_bottles}"
                      end

    "#{num_bottles_str} #{bottle_inflection(num_bottles)}"
  end

  def bottle_inflection(num_bottles)
    if num_bottles == 1
      'bottle'
    else
      'bottles'
    end
  end

  def action(num_bottles)
    if num_bottles == 0
      'Go to the store and buy some more'
    else
      "Take #{action_noun(num_bottles)} down and pass it around"
    end
  end

  def action_noun(num_bottles)
    if num_bottles == 1
      'it'
    else
      'one'
    end
  end
end
