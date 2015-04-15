class Array

  def keep(&block)
    select { |e| yield(e)}
  end

  def discard
    select { |e| !yield(e)}
  end
end
