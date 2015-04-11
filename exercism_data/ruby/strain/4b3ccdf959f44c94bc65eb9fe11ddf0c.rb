class Array
  def keep &block
    select { |obj| block.call(obj)}
  end

  def discard &block
    reject { |obj| block.call(obj)}
  end
end
