class Array
  def accumulate
    return self unless block_given?
    acc = []
    for i in (0..(size-1))
      acc << yield(self[i])
    end
    acc
  end
end
