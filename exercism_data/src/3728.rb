def compute(first, second)
    first.each_char.zip(second.each_char).inject(0) do |distance, pair|
      pair[0] == pair[1] ? distance : distance + 1
    end
  end