class Bst < Array
  attr_accessor :data, :left, :right
  def initialize(data)
    self.data = data
    self << data
  end

  def insert(data)
    if  self.data < data
      if (self.right )
        self.right.insert(data)
      else
        self.right = Bst.new(data)
      end
    else
      if (self.left )
        self.left.insert(data)
      else
        self.left = Bst.new(data)
      end
    end
    self << data
    self.sort!
  end
end
