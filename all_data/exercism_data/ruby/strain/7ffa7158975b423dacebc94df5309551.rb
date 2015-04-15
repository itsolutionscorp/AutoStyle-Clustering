class Array
  def keep &block
    self.map do |x|
      if block.(x)
        x
      end
    end.compact
  end

  def discard &block
    self.map do |x|
      if !block.(x)
        x
      end
    end.compact
  end
end
