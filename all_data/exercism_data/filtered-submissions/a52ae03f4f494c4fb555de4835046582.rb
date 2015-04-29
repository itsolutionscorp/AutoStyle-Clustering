def compute(src, dest)
    distance = 0

    length = [src, dest].map(&:length).min

    i = 0
    while i < length
      unless src[i] == dest[i]
        distance += 1
      end
      i += 1
    end

    distance
  end