def compute(string1, string2)
    strings = [string1, string2].sort_by{ |s| s.length }

    serialized = strings.map{ |s| s.split('') }

    pairs = serialized[0].zip(serialized[1])

    pairs.reduce(0) do |total, pair|
      pair[0] == pair[1] ? total : total + 1
    end
  end