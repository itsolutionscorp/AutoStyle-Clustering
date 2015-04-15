class Proverb
  attr_reader :stuff, :qualifier

  def initialize(*stuff, qualifier: '')
    @qualifier = qualifier + ' '
    @stuff = stuff
  end

  def to_s
    stuff.each_cons(2).reduce('') do |proverb, (thing1, thing2)|
      proverb + for_want(thing1, thing2)
    end + all(stuff.first)
  end

  private

  def for_want(thing1, thing2)
    "For want of a #{thing1} the #{thing2} was lost.\n"
  end

  def all(thing)
    "And all for the want of a #{(qualifier + thing).strip}."
  end
end
