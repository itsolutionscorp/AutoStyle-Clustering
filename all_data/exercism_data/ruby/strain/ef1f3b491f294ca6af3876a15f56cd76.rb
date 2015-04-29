class Array

  def keep(&block)
    if self.length == 0
        return self
      else
        result = []
        self.each do |node|
          if block.call(node) == true
          result << node
        end
      end
      result
    end
  end

  def discard(&block)
    if self.length == 0
        return self
      else
        result = []
        self.each do |node|
          if block.call(node) == false
          result << node
        end
      end
      result
    end
  end
  
end
