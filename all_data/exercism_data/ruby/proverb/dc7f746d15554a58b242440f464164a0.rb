# rubocop:disable Documentation
class Proverb
  def initialize(*elements, qualifier: '')
    @elements = elements
    @qualifier = qualifier
    @qualifier += ' ' unless @qualifier.empty?
  end

  def to_s
    ''.tap do |output|
      @elements.each_cons(2) do |e1, e2|
        output << "For want of a #{e1} the #{e2} was lost.\n"
      end

      output << "And all for the want of a #{@qualifier}#{@elements.first}."
    end
  end
end
