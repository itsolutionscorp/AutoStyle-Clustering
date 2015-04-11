class Array
  def keep(&block)
    kept = []
    self.each do |element|
      kept << element if block.call(element)
    end
    kept
  end

  def discard(&block)
    kept = []
    self.each do |element|
      kept << element unless block.call(element)
    end
    kept
  end
end
