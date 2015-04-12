class Hamming
  def compute( a, b )

    if a.length > b.length
      a = a[ 0, b.length ]
    end

    count = 0
    a.chars.each_with_index do |char, i|
      count += 1 if char != b.chars[ i ]
    end
    count
  end
end
