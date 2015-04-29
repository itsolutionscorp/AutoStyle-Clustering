class CustomSet
  def initialize list=[]
    @list = list.to_a.uniq
  end
  
  def each
    @list.each
  end
  
  def to_a
    to_list
  end
  
  def to_list
    @list.to_a
  end
  
  def length
    @list.length
  end
  
  def size
    length
  end
  
  def disjoint? other
    intersection(other).size.zero?
  end
  
  def member? item
    @list.find{ |el| el.equal? item }
  end
  
  def subset? other
    intersection(other).size == other.to_a.length
  end
  
  def empty
    CustomSet.new []
  end
  
  def empty!
    @list = []
    self
  end
  
  def put *items
    CustomSet.new @list + items
  end
  
  def put! *items
    @list = @list + items
    self
  end
  
  def delete item
    CustomSet.new @list.reject{ |el| el.equal? item }
  end
  
  def delete! item
    @list.reject!{ |el| el.equal? item }
    self
  end
  
  def difference other
    CustomSet.new @list - other.to_a
  end
  
  def intersection other
    CustomSet.new @list & other.to_a
  end
  
  def union other
    CustomSet.new @list | other.to_a
  end
  
  def == other
    to_a.sort == other.to_a.sort
  end
  
end
