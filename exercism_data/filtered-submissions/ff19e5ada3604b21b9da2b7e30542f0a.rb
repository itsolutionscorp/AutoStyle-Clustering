def compute(this, that)
    char_pairs = this.chars.zip( that.chars )
    distance = 0

    char_pairs.each do |pair|
      if pair[0] != pair[1]
        distance += 1
      end
    end

    return distance
  end