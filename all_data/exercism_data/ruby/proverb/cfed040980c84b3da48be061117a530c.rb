class Proverb
  def initialize(*wants, qualifier: nil)
    @wants = wants
    @qualifier = qualifier
  end

  def to_s
    proverb = ''
    @wants.each_cons(2) { |w| proverb << verse(w.first, w.last) }
    last_verse = last_verse(@qualifier)

    proverb << last_verse
  end

  private

  def verse(want, sacrifice)
    "For want of a #{want} the #{sacrifice} was lost.\n"
  end

  def last_verse(qualifier=nil)
    qualifier = "#{qualifier} " unless qualifier.nil?
    "And all for the want of a #{qualifier}#{@wants.first}."
  end
end
