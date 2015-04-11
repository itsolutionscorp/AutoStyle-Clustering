class Beer

  def sing(start_verse, end_verse = 0)
    requested_verses = ""

    start_verse.downto(end_verse).each do |verse_number|
      requested_verses << Verse.new(verse_number).to_s + "\n"
    end

    requested_verses
  end

  def verse(verse_number)
    Verse.new(verse_number).to_s
  end

end

class Verse
  attr_reader :bottles_qty

  def initialize(verse_number)
    @bottles_qty = verse_number
  end

  def to_s
    if zero_bottles?
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "#{bottles_qty} #{bottle_s} of beer on the wall, #{bottles_qty} #{bottle_s} of beer.\nTake #{one_it} down and pass it around, #{bottles_remaining} #{one_less_bottle_s} of beer on the wall.\n"
    end
  end

  private

  def bottle_s
    if zero_bottles?
      'bottles'
    elsif one_bottle?
      'bottle'
    elsif bottles_qty > 1
      'bottles'
    end
  end

  def one_it
    if one_bottle?
      'it'
    else
      'one'
    end
  end

  def bottles_remaining
    if one_less_bottle < 1
      'no more'
    else
      one_less_bottle
    end
  end

  def one_less_bottle_s
    if one_less_bottle == 1
      'bottle'
    elsif one_less_bottle == 0
      'bottles'
    else
      bottle_s
    end
  end

  def one_less_bottle
    bottles_qty - 1
  end

  def zero_bottles?
    bottles_qty == 0
  end

  def one_bottle?
    bottles_qty == 1
  end

end
