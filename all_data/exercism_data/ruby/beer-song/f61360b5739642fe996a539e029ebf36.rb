# Let's concede that if one where willing to put a bunch of
# 'if' statments in this it would be shorter, and that doing
# so would likely be the best solution for the beer song (which
# has not changed since the dawm of time).
#
# Let's.
#
# Instead, let's imagine that we are forced (forced, I say!) to
# solve this via inheritance.
#
# Hmmm.
#
# To me, the most interesting thing about this solution is that it
# fiercely delineates the ways in which verse 1 and 0 differ from
# the rest.  The solution that uses 'if' statements spreads the
# differences out all over and thus the set of differences are
# harder to see.

class Beer # or any beverage, in any container, at any location

  def sing(start, stop=0, opts={})
    stringify(start.downto(stop).collect {|i| verse(i, opts)})
  end

  def verse(n, opts={})
    singer(n, opts).verse(n)
  end

  private
  def stringify(verses)
    verses.join("\n") + "\n"
  end

  def singer(n, opts)
    begin
      Object.const_get("BeverageVerse#{n}")
    rescue NameError
      BeverageVerse
    end.new(opts)
  end
end


class BeverageVerse
  attr_reader :beverage, :raw_container, :location

  def initialize(opts)
    @beverage      = opts[:beverage]  || 'beer'
    @raw_container = opts[:container] || 'bottle'
    @location      = opts[:location]  || 'on the wall'
  end

  def verse(n)
    start  = starting_quantity_phrase(n)
    finish = ending_quantity_phrase(n)
    "#{beverages_at_location(start)}, #{beverages(start).downcase}.\n#{action}, #{beverages_at_location(finish)}.\n"
  end

  private
  def beverages_at_location(quantity)
    "#{beverages(quantity)} #{location}"
  end

  def beverages(quantity)
    "#{container_of(quantity)} of #{beverage}"
  end

  def container_of(quantity)
    "#{quantity} #{pluralize_if_needed(raw_container, quantity)}"
  end

  def action
    "Take #{reference_to_container} down and pass it around"
  end

  def starting_quantity_phrase(n)
    n.to_s
  end

  def ending_quantity_phrase(n)
    (n-1).to_s
  end

  def reference_to_container
    'one'
  end

  def pluralize_if_needed(str, quantity_phrase)
    return str if quantity_phrase.to_i == 1
    str + 's'
  end

  # I find it a little awkward that this method is not referenced in
  # this class (it's sent by subclasses). This suggests that I need
  # an abstract superclass and that there should be a parallel
  # BeverageVerseN subclass....but I must say, that seems like double
  # overkill for this one thing..
  def zero_quantity_phrase
    'No more'
  end

end

class BeverageVerse0 < BeverageVerse

  def starting_quantity_phrase(n)
    zero_quantity_phrase
  end

  def ending_quantity_phrase(n)
    '99'
  end

  def action
    'Go to the store and buy some more'
  end
end

class BeverageVerse1 < BeverageVerse

  def ending_quantity_phrase(n)
    zero_quantity_phrase.downcase
  end

  def reference_to_container
    'it'
  end

end
