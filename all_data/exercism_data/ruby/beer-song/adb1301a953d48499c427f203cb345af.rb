class Beer
  attr_reader :start_verse, :end_verse

  def sing(start_verse, end_verse = 0)
    @start_verse = start_verse
    @end_verse = end_verse

    requested_verses
  end

  def verse(verse_number)
    singer = Singer.new
    singer.recall_verse(verse_number)
  end

  private

  def requested_verses
    requested_verse_numbers = Array(end_verse..start_verse).reverse
    requested_verses = ""

    requested_verse_numbers.each do |requested_verse_number|
      requested_verses << verse(requested_verse_number) + "\n"
    end
    requested_verses
  end

end

class Singer
  attr_accessor :bottles_of_beer, :verse_number

  def initialize
    @bottles_of_beer = nil
  end

  def recall_verse(verse_number)
    @bottles_of_beer = verse_number
    "#{dynamic_phrase_1} of beer on the wall, #{dynamic_phrase_2} of beer.\n#{dynamic_phrase_3}, #{dynamic_phrase_4} of beer on the wall.\n"
  end

  private

   def dynamic_phrase_1
    if zero_bottles?
      "No more bottles"
    else
      "#{bottles_of_beer} #{bottle_s}"
    end
  end

  def dynamic_phrase_2
    if zero_bottles?
      "no more #{bottle_s}"
    else
      "#{bottles_of_beer} #{bottle_s}"
    end
  end

  def dynamic_phrase_3
    one_it = 'one'

    if zero_bottles?
      'Go to the store and buy some more'
    else
      if one_bottle?
        one_it = 'it'
      end
      "Take #{one_it} down and pass it around"
    end
  end

  def dynamic_phrase_4
    if one_bottle?
      "no more bottles"
    elsif zero_bottles?
      "99 #{bottle_s}"
    else
      @bottles_of_beer -= 1
      "#{bottles_of_beer} #{bottle_s}"
    end
  end

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

  def zero_bottles?
    bottles_of_beer == 0
  end

end
