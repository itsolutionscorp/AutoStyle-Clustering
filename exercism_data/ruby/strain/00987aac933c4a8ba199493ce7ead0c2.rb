class Array
  def keep(&block)
    filter(true, &block)
  end

  def discard(&block)
    filter(false, &block)
  end

  def filter(keep_if_block_returns_true=true, &block)
    inject(self.class.new) do |new_array, x|
      if yield(x) == keep_if_block_returns_true
        new_array << x
      end
      new_array
    end
  end
end
