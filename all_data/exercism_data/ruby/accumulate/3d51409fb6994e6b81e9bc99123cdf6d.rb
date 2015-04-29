class Array
  def accumulate
    op = []
    for e in self do
      op << yield(e)
    end
    op
  end
end
