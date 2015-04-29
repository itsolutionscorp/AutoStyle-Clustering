def compute(a, b)
    a = a.split("")
    b = b.split("")

    a.each_with_index.inject(0) { |result, (element, idx)|
      if b[idx] != element
        result = result + 1
      else
        result
      end
    }
  end