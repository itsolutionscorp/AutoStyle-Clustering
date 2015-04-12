def compute(first,second)
    len = first.length < second.length ? first.length : second.length
    (0...len).inject(0) { |memo, i|
      first[i] == second[i] ? memo : memo + 1
    }
  end