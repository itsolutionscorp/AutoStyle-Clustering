class CustomSet

  attr_accessor :set

  def initialize(array=[])
    @set = array.to_a.uniq.sort
    self
  end

  def ==(custom_set_instance)
    self.set == custom_set_instance.set
  end

  def delete(item)
    self.set -= [item]
    self
  end

  def difference(custom_set_instance)
    self.set -= custom_set_instance.set
    self
  end

  # No overlap
  def disjoint?(custom_set_instance)
    (self.set & custom_set_instance.set).empty?
  end

  def empty
    self.set = []
    self
  end

  def intersection(custom_set_instance)
    self.class.new(self.set & custom_set_instance.set)
  end

  def member?(n)
    self.set.map(&:to_s).include?(n.to_s)
  end

  def put(n)
    self.set << n if !self.set.map(&:to_s).include?(n.to_s)
    self.set.sort!
    self
  end

  def size
    self.set.size
  end

  def subset?(custom_set_instance)
    (custom_set_instance.set - self.set).empty?
  end

  def to_a
    self.set
  end

  def union(custom_set_instance)
    self.set += custom_set_instance.set
    self.set.sort!
    self
  end

end
