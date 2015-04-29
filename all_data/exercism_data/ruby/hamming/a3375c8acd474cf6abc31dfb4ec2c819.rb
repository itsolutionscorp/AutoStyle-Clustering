class Hamming
  def self.compute( a, b )
    i, count = 0, 0

    while a[ i ] && b[ i ]
      count += 1 if a[ i ] != b[ i ]
      i += 1
    end

    count
  end
end
