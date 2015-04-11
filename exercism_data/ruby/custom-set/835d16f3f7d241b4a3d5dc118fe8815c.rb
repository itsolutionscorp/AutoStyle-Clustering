class CustomSet
  include Enumerable

  def initialize(elements = [])
    @elements = elements.to_a.uniq.sort
  end

  def size
    elements.size
  end

  def to_list
    to_a
  end

  def ==(other)
    other == elements
  end

  def each(&block)
    elements.each(&block)
  end

  def put(element)
    create to_a << element
  end

  def delete(element)
    create reject &equal(element)
  end

  def member?(element)
    find &equal(element)
  end

  def subset?(other)
    intersection(other) == other
  end

  def disjoint?(other)
    intersection(other) == empty
  end

  def difference(other)
    create reject &members_of(other)
  end

  def intersection(other)
    create find_all &members_of(other)
  end

  def union(other)
    create to_a.push(*other.to_a)
  end

  def empty
    create
  end

private 
  attr_reader :elements

  def create(elements = [])
    self.class.new elements
  end

  def equal(element)
    lambda { |e| e.eql? element }
  end

  def members_of(other)
    lambda { |e| other.member? e }
  end

end
