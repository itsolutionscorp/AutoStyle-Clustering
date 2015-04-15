class Array
  def keep
    output = []
    i = 0
    while yield i
      if yield i
        output << i
      end
      i += 1
    end
    output
  end
end
