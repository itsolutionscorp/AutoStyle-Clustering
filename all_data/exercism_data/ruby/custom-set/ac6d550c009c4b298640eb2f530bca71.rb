class CustomSet
  attr_reader :set

  def initialize arr = []
    @set = arr.sort.uniq
  end

  def == other
    set == other.set
  end

  def delete n
    @set.delete n unless n.class == Float
    self
  end

  def difference other
    new_set(set - other.set)
  end

  def disjoint? other
    if @set.empty? || other.set.empty?
      true
    elsif @set.last.class == other.set.first.class
      @set.last != other.set.first && @set.last.class
    else
      true
    end
  end

  def empty
    @set = []
    self
  end

  def intersection other
    c = @set + other.set
    occurences = c.each_with_object(Hash.new(0)) do |e, a|
      a[e] += 1
    end
    c.select! do |x|
      occurences[x] > 1
    end
    new_set(c.uniq)
  end

  def member? n
    @set.any? { |x| x == n && x.class == n.class }
  end

  def put n
    new_set(@set << n)
  end

  def size
    @set.size
  end

  def to_list
    set
  end

  def subset? ss
    a = ss.set
    return true if a.empty?
    s = a.size
    b = set.each_cons(s).to_a
    b.any? do |c|
      c.each_index do |i|
        return false unless c[i] == a[i] && c[i].class == a[i].class
      end
    end
  end

  def union other
    new_set(set + other.set)
  end

  private

  def new_set(arr = [])
    self.class.new(arr)
  end
end
