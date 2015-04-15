class CustomSet
  
  def initialize(list=[])
    @cs =[]
    list.each do |n|
      put(n)
    end
  end
  
  def size
    @cs.size
  end  
  
  def each(&blk)
    @cs.each do |n|
      blk.call(n)
    end
  end  
  
  def index(n)
    @cs.index { |x| x.eql?(n) }
  end 
  
  def ==(other_object)
    return false unless other_object.class == self.class
    return false unless other_object.size ==self.size
    other_object.each do |n|
      return false unless index(n)  
    end   
    true
  end
  
  def eql?(other)
    self == other
  end

  def hash
    @cs.reduce(:+){ |n| n.hash }
  end
  
  def delete(n)
    @cs.delete(n) if index(n)
    self
  end   
  
  def difference(other)
    CustomSet.new(@cs.select { |n| other.index(n).nil? })
  end  
  
  def disjoint?(other)
    union(other).size == self.size + other.size 
  end
  
  def intersection(other)
    CustomSet.new(@cs.select { |n| other.index(n) })
  end 
  
  def subset?(other)
    self.size >= other.size && intersection(other) == other
  end 
  
  def union(other)
    u = CustomSet.new(@cs)
    other.each { |n| u.put(n) }
    u 
  end    
  
  def member?(n)
    index(n)
  end 
  
  def put(n)
    @cs << n unless index(n)
    self
  end   
  
  def empty
    @cs = []
    self
  end  
  
  def to_list
    @cs
  end  
  
end
