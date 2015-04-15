class CustomSet
  include Enumerable

  def initialize(elements = [])
    @elements = elements.to_a.uniq.sort
  end

  def ==(other)
    other == elements
  end

  def each(&block)
    elements.each(&block)
  end

  def delete(element)
    create reject { |e| e.eql? element }
  end

  def difference(other)
    create reject { |e| other.member? e }
  end

  def intersection(other)
    create find_all { |e| other.member? e }
  end

  def disjoint?(other)
    intersection(other) == empty
  end

  def empty
    create
  end

  def member?(element)
    find { |e| e.eql? element }
  end

  def put(element)
    create elements.clone << element
  end

  def size
    elements.size
  end

  def subset?(other)
    other.all? { |e| self.member? e }
  end

  def to_list
    to_a
  end

  def union(other)
    create elements.clone.push(*other.to_a)
  end

private 
  attr_reader :elements

  def create(elements = [])
    self.class.new elements
  end

end
