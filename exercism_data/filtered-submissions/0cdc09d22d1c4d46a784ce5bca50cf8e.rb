def compute(xs, ys)
    (xs.split '').zip(ys.split '').reduce(0) do |count, (x, y)|
      return nil unless x && y
      if x != y
        count + 1
      else
        count
      end
    end
  end