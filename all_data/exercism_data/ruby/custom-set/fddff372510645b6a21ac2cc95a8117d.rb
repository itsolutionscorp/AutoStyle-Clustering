class CustomSet
  class Node
    attr_reader :datum
    def initialize(datum)
      @datum = datum
    end
  end

  def initialize(data = [])
    @data = parse_data(data)
  end

  def delete(datum)
    @data.reject! { |node| node.datum.eql? datum }
    self
  end

  def put(datum)
    @data.any? { |node| node.datum.eql? datum } || @data << Node.new(datum)
    self
  end

  def difference(other)
    CustomSet.new(nodes - other.nodes)
  end

  def disjoint?(other)
    difference(other).size == other.size
  end

  def size
    @data.length
  end

  def empty
    initialize
    self
  end

  def intersection(other)
    CustomSet.new(nodes & other.nodes)
  end

  def member?(datum)
    nodes.find { |d| d.eql?(datum) }
  end

  def subset?(other)
    (other.nodes - nodes).length == 0
  end

  def union(other)
    CustomSet.new(nodes | other.nodes)
  end

  def to_a
    nodes
  end

  def nodes
    @data.map(&:datum).sort
  end

  def ==(other)
    nodes == other.nodes
  end

  private

  def parse_data(data)
    data.to_a.uniq.map { |datum| Node.new(datum) }
  end
end
