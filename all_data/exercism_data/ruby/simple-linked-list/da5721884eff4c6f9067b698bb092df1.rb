class Element
  def self.from_a arr
    arr.reverse_each.inject(nil){|nxt, datum| new(datum,nxt)}
  end
  
  def self.to_a start=nil
    arr = []
    each_datum start, &arr.method(:push)
    arr
  end
  
  def self.reverse elem
    rest = nil
    each_datum(elem) {|datum| rest = new(datum,rest)}
    rest
  end
  
  def self.each_datum elem
    until elem.nil?
      yield elem.datum
      elem = elem.next
    end
  end
  
  attr_reader :datum, :next
  def initialize datum, nxt=nil
    @datum = datum
    @next = nxt
  end
  
  def to_a
    self.class.to_a self
  end
  
  def reverse
    self.class.reverse self
  end
end
