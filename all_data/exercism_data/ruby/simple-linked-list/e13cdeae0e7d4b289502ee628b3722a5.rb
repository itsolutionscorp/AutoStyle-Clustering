class Element
  attr_accessor :datum, :next

  def initialize(datum, next_element)
    @datum = datum
    @next = next_element
  end

  def reverse
    return Element.new(@datum, nil) if @next.nil?
    Element.new(@next.datum, self)
  end

  def to_a
    Element.to_a(self)
  end

  def self.to_a(element)
    traverse(element)
  end

  def self.from_a(args)
    args.to_a.reverse.inject(nil){ |prev, x| Element.new(x, prev) }
  end

  private

  def self.traverse(element)
    return [] if element.nil?

    [element.datum].concat(traverse(element.next))
  end

end
