class Beer
  attr_accessor :bottle_quantity
  attr_reader :start_verse, :end_verse

  def initialize
    @bottle_quantity = nil
  end

  def sing(start_verse, end_verse = 0)
    @start_verse = start_verse
    @end_verse = end_verse

    requested_verses
  end

  def verse(verse_number)
    @bottle_quantity = verse_number

    "#{dynamic_phrase_1} of beer on the wall, #{dynamic_phrase_2} of beer.\n#{dynamic_phrase_3}, #{dynamic_phrase_4} of beer on the wall.\n"

  end

  private

  # This creates the jonined string of requested verses
  def requested_verses
    requested_verse_numbers = Array(end_verse..start_verse).reverse
    requested_verses = ""

    requested_verse_numbers.each do |requested_verse_number|
      requested_verses << verse(requested_verse_number) + "\n"
    end

    requested_verses
  end

  # Handles the following exceptions:
  ## Verse 0: **No more bottles** of beer on the wall, no more bottles of beer.
  ## Verse 1: **1 bottle** of beer on the wall, 1 bottle of beer.
  ## Verse x: **x bottles** of beer on the wall, x bottles of beer.
  def dynamic_phrase_1

    # Verse 1: **1 bottle**
    # Verse 0: **No more bottles**
    if zero_bottles?
      "No more bottles"
    # Verse 1: **1 bottle**
    # Verse x: **x bottles**
    else
      "#{bottle_quantity} #{bottle_s}"
    end
  end

  # Handles the following exceptions:
  ## Verse 1: 1 bottle of beer on the wall, **1 bottle** of beer.
  ## Verse 0: No more bottles of beer on the wall, **no more bottles** of beer.
  ## Verse x: x bottles of beer on the wall, **x bottles** of beer.
  def dynamic_phrase_2
    # Verse 0: **no more bottles**
    if zero_bottles?
      "no more #{bottle_s}"
    else
      # Verse 1: **1 bottle**
      # Verse x: **x bottles**
      "#{bottle_quantity} #{bottle_s}"
    end
  end

  # Handles the following exceptions:
  ## Verse 1: Take **it** down and pass it around, no more bottles of beer on the wall.
  ## Verse 0: **Go to the store and buy some more**, 99 bottles of beer on the wall.
  ## Verse x: **Take one down and pass it around**, x bottles of beer on the wall.
  def dynamic_phrase_3
    one_it = 'one'

    # Verse 0: **Go to the store and buy some more**
    if zero_bottles?
      'Go to the store and buy some more'
    else
      # Verse 1: **it**
      if one_bottle?
        one_it = 'it'
      end
      # Verse x: **Take one down and pass it around**
      "Take #{one_it} down and pass it around"
    end
  end

  # Handles the following exceptions:
  ## Verse 0: Go to the store and buy some more, **99 bottles** of beer on the wall.
  ## Verse 1: Take it down and pass it around, **no more bottles** of beer on the wall.
  ## Verse 2: Take one down and pass it around, **1 bottle** of beer on the wall.
  ## Verse x: Take one down and pass it around, **x bottles** of beer on the wall.
  def dynamic_phrase_4

    # Verse 1: **no more bottles**
    if one_bottle?
      "no more bottles"
    # Verse 0: **99 bottles**
    elsif zero_bottles?
      "99 #{bottle_s}"
    else
      # Verse 2: **bottle**
      # Verse x: **x bottles**
      @bottle_quantity -= 1
      "#{bottle_quantity} #{bottle_s}"
    end
  end

  # This returns the correct plural or singular usage of bottle
  def bottle_s
    if bottle_quantity > 1 || bottle_quantity == 0
      'bottles'
    elsif one_bottle?
      'bottle'
    end
  end

  def one_bottle?
    bottle_quantity == 1
  end

  def two_bottles?
    bottle_quantity == 2
  end

  def zero_bottles?
    bottle_quantity == 0
  end

end
