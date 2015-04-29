class CustomSet
  attr_reader :list

  def initialize(list=[])
    @list = sanitize(list)
  end

  def ==(other)
    list.sort == other.list.sort
  end

  def delete(datum)
    new_list = list - [datum]
    CustomSet.new(new_list)
  end

  def difference(other)
    new_list = list - other.list
    CustomSet.new(new_list)
  end

  def disjoint?(other)
    list - other.list == list
  end

  def empty
    CustomSet.new
  end

  def intersection(other)
    new_list = list.select{|item| other.list.any?{|other_item| other_item.eql?(item)}}
    CustomSet.new(new_list)
  end

  def member?(datum)
    list.any?{|item| item.eql?(datum)}
  end

  def put(datum)
    new_list = list + [datum]
    CustomSet.new(new_list.uniq)
  end

  def size
    list.length
  end

  def subset?(other)
    other.list.all?{|other_item| list.any?{|item| item.eql?(other_item)}}
  end

  def to_list
    list
  end

  def union(other)
    new_list = list + other.list
    CustomSet.new(new_list.uniq)
  end

  private

  def sanitize(collection)
    collection.to_a.uniq
  end
end
