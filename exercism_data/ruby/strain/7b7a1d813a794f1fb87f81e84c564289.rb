class Array

  def keep(&block)
    modify(&block)
  end

  def discard(&block)
    modify(true, &block)
  end

  private

    def modify(remove=false, &block)
      i = length - 1
      while i >= 0
        delete_at(i) unless remove ^ block.call(self[i])
        i -= 1
      end
      self
    end
end
