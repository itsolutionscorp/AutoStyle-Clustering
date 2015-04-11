class Beer
  attr_reader :start_verse, :end_verse

  def sing(start_verse, end_verse = 0)
    @start_verse = start_verse
    @end_verse = end_verse

    requested_verses
  end

  def verse(verse_number)
    bottles_of_beer = verse_number

    if zero_bottles?(bottles_of_beer)
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "#{dynamic_phrase_1(bottles_of_beer)} of beer on the wall, #{dynamic_phrase_2(bottles_of_beer)} of beer.\n#{dynamic_phrase_3(bottles_of_beer)}, #{dynamic_phrase_4(bottles_of_beer)} of beer on the wall.\n"
    end
  end

  private

  def requested_verses
    requested_verses = ""

    start_verse.downto(end_verse).each do |requested_verse_number|
      requested_verses << verse(requested_verse_number) + "\n"
    end
    requested_verses
  end

  def dynamic_phrase_1(bottles_of_beer)
    "#{bottles_of_beer} #{bottle_s(bottles_of_beer)}"
  end

  def dynamic_phrase_2(bottles_of_beer)
    "#{bottles_of_beer} #{bottle_s(bottles_of_beer)}"
  end

  def dynamic_phrase_3(bottles_of_beer)
    one_it = 'one'

    if one_bottle?(bottles_of_beer)
      one_it = 'it'
    end
    "Take #{one_it} down and pass it around"
  end

  def dynamic_phrase_4(bottles_of_beer)
    if one_bottle?(bottles_of_beer)
      'no more bottles'
    else
      bottles_of_beer -= 1
      "#{bottles_of_beer} #{bottle_s(bottles_of_beer)}"
    end
  end

  # This returns the correct plural or singular usage of bottle
  def bottle_s(bottles_of_beer)
    if bottles_of_beer > 1 || bottles_of_beer == 0
      'bottles'
    elsif one_bottle?(bottles_of_beer)
      'bottle'
    end
  end

  def one_bottle?(bottles_of_beer)
    bottles_of_beer == 1
  end

  def two_bottles?(bottles_of_beer)
    bottles_of_beer == 2
  end

  def zero_bottles?(bottles_of_beer)
    bottles_of_beer == 0
  end

end
