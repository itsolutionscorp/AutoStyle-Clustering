class Proverb
  def initialize(*chain, qualifier: '')
    @chain = chain
    @qualifier = qualifier
  end

  def to_s
    chain.each_with_object('') do |consequence, proverb|
      proverb << " the #{consequence} was lost.\n" unless first?(consequence)
      proverb << "For want of a #{consequence}"    unless last?(consequence)

      proverb << "And all for the want of a #{first_consequence_with_qualifier}." if last?(consequence)
    end
  end

  private

  attr_reader :chain, :qualifier

  def first?(consequence)
    consequence == chain.first
  end

  def last?(consequence)
    consequence == chain.last
  end

  def first_consequence_with_qualifier
    "#{qualifier} #{chain.first}".strip
  end
end
