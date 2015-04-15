class Beer
  attr_reader :start_verse, :end_verse

  def sing(start_verse, end_verse = 0)
    @start_verse = start_verse
    @end_verse = end_verse

    requested_verses
  end

  def verse(verse_number)
    bottles_of_beer = verse_number
    dynamic = Dynamic.new(bottles_of_beer)

    if dynamic.zero_bottles?
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "#{dynamic.phrase_1} of beer on the wall, #{dynamic.phrase_2} of beer.\n#{dynamic.phrase_3}, #{dynamic.phrase_4} of beer on the wall.\n"
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

end

class Dynamic
  attr_reader :bottles_of_beer

  def initialize(bottles_of_beer)
    @bottles_of_beer = bottles_of_beer
  end

  def phrase_1
    "#{bottles_of_beer} #{bottle_s}"
  end

  def phrase_2
    "#{bottles_of_beer} #{bottle_s}"
  end

  def phrase_3
    one_it = 'one'

    if one_bottle?
      one_it = 'it'
    end
    "Take #{one_it} down and pass it around"
  end

  def phrase_4
    if one_bottle?
      'no more bottles'
    elsif two_bottles?
      "1 bottle"
    else
      "#{bottles_of_beer - 1} #{bottle_s}"
    end
  end

  def zero_bottles?
    bottles_of_beer == 0
  end

  private

  # This returns the correct plural or singular usage of bottle
  def bottle_s
    if bottles_of_beer > 1 || bottles_of_beer == 0
      'bottles'
    elsif one_bottle?
      'bottle'
    end
  end

  def one_bottle?
    bottles_of_beer == 1
  end

  def two_bottles?
    bottles_of_beer == 2
  end

end
