class Element
  attr_reader :datum

  def initialize(datum, link)
    @datum = datum
    @link = link
  end

  def self.to_a (element)
    array = []
    until element == nil
      array << element.datum
      element = element.next
    end
    array
  end

  def reverse
    self
  end

  def next
    @link
  end

end
