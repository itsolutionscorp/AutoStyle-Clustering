class Beer

  def sing(start_verse, end_verse = 0)
    verses = start_verse.downto(end_verse).inject('') do |verses, verse_number|
      verses + Verse.new(verse_number).to_s + "\n"
    end
  end

  def verse(verse_number)
    Verse.new(verse_number).to_s
  end

end

class Verse
  attr_reader :quantity

  def initialize(verse_number)
    @quantity = verse_number
  end

  def to_s
    if zero_bottles?
      LastVerse.new(quantity).to_s
    else
      PlainVerse.new(quantity).to_s
    end
  end

  private

  def zero_bottles?
    quantity == 0
  end

end

class PlainVerse
  attr_reader :quantity

  def initialize(verse_number)
    @quantity = verse_number
  end

  def to_s
    "#{quantity} #{bottle_s} of beer on the wall, #{quantity} #{bottle_s} of beer.\nTake #{one_it} down and pass it around, #{bottles_remaining} #{one_less_bottle_s} of beer on the wall.\n"
  end

  private

  def bottle_s
    if one_bottle?
      'bottle'
    else
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
    quantity - 1
  end

  def one_bottle?
    quantity == 1
  end

end

class LastVerse
  attr_reader :quantity

  def initialize(quantity)
    @quantity = quantity
  end

  def to_s
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

end
