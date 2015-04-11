class Proverb
  def initialize(*things, qualifier: nil)
    @things = things
    @qualifier = qualifier
  end

  def to_s
    @proverb ||= proverb
  end

  private

  def statement(desired, lost)
    "For want of a #{desired} the #{lost} was lost.\n"
  end

  def conclusion
    vanity_object = " #{@qualifier} #{@things.first}".squeeze
    "And all for the want of a#{vanity_object}."
  end

  def proverb
    proverb = ""
    pairs = @things[0..-2].zip(@things[1..-1])
    pairs.each do |pair|
      proverb += statement(pair.first, pair.last)
    end
    proverb += conclusion
  end
end
