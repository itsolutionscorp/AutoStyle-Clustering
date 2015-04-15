class Proverb
  def initialize(*the_lost)
    @the_lost = the_lost
    @qualifier = extract_qualifier the_lost
  end

  def to_s
    proverb = ''
    (the_lost.size-1).times { |count| proverb += consequence count }
    proverb += last_special_consequence
  end

  private

  attr_reader :the_lost, :qualifier

  def consequence count
    "For want of a #{the_lost[count]} the #{the_lost[count+1]} was lost.\n"
  end

  def last_special_consequence
    "And all for the want of a #{qualifier}#{spacer_if_qualified}#{the_lost[0]}."
  end

  def spacer_if_qualified
    qualifier.empty? ? '' : ' '
  end

  def extract_qualifier the_lost
    the_lost.last.is_a?(Hash) ? the_lost.pop[:qualifier] : ''
  end
end
