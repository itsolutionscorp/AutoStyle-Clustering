def compute (from, to)
    numberOfChanges = 0
    from.split("").each_with_index do |c, i|
      numberOfChanges += 1 if i < to.length && c != to[i]
    end
    numberOfChanges
  end