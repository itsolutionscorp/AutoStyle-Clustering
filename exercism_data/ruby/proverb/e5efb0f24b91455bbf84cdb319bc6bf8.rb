class Proverb
  def initialize(*wants, qualifier: nil)
    @wants = wants
    @qualifier = qualifier
    @cached_proverb = nil
  end

  def to_s
    return @cached_proverb unless @cached_proverb.nil?

    ending = proverb_ending(@wants.first, @qualifier)
    proverb = ''
    @wants.each_cons(2) { |w| proverb << verse(w.first, w.last) }

    @cached_proverb = proverb << ending
  end

  private

  def verse(want, sacrifice)
    "For want of a #{want} the #{sacrifice} was lost.\n"
  end

  def proverb_ending(want, qualifier=nil)
    qualifier = "#{qualifier} " unless qualifier.nil?
    "And all for the want of a #{qualifier}#{want}."
  end
end
