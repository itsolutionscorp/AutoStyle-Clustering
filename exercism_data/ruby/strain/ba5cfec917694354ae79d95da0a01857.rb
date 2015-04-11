class Array

  def keep
    output = []
    each do |x|
      if yield x
        output << x
      end
    end
    output
  end

  def discard
    output = []
    each do |x|
      if (yield x) == false
        output << x
      end
    end
    output
  end
end
