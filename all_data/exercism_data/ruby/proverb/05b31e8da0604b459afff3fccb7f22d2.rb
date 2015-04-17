class Proverb
  def initialize(*the_lost)
    @the_lost = the_lost
    @qualifier = extract_qualifier the_lost
  end

  def to_s
    verse
  end

  private

  attr_reader :the_lost, :qualifier

  def verse
    body_of_verse + end_of_verse
  end

  def body_of_verse
    verse = ''
    (the_lost.size-1).times { |count| verse += line_of_verse count }
    verse
  end

  def end_of_verse
    "And all for the want of a #{qualifier}#{spacer_if_qualified}#{the_lost[0]}."
  end

  def line_of_verse count
    "For want of a #{the_lost[count]} the #{the_lost[count+1]} was lost.\n"
  end

  def spacer_if_qualified
    qualifier.empty? ? '' : ' '
  end

  def extract_qualifier the_lost
    the_lost.last.is_a?(Hash) ? the_lost.pop[:qualifier] : ''
  end
end