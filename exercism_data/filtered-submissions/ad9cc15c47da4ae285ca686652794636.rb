def compute(start, finish)
    pairs = start.chars.zip(finish.chars)
    pairs.select { |old,new| old && new && old != new }.length
  end