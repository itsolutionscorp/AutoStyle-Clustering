def compute a, b
    [a.length, b.length].min - a.split("").zip(b.split("")).select {|elem| elem[0] == elem[1]}.count
  end