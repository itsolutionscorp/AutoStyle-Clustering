class Bst
  attr_reader :data
  @@array = []
  
  def initialize(data)
    @data = data
    @child = {l:nil, r:nil}
  end

  def insert(datum)
    if datum <= data
      unless left
        self.left = datum
      else
        left.insert(datum)
      end
    else
      unless right
        self.right = datum
      else
        right.insert(datum)
      end
    end
  end

  def each
    @@array = []
    assemble.each do |v|
      yield v
    end
  end

  def assemble
    left.assemble if left
    add
    right.assemble if right
    @@array
  end

  def add
    @@array.push(@data)
  end

  def left= datum
    @child[:l] = Bst.new(datum)
  end
  
  def right= datum
    @child[:r] = Bst.new(datum)
  end
  
  def left
    @child[:l]
  end

  def right
    @child[:r]
  end
end
