class Hamming

  def self.compute(strand1, strand2)
    one = strand1.split('')
    two = strand2.split('')

    count = 0
    smallest_array_size(one, two).times do |i|
      count += 1 if one[i].capitalize != two[i].capitalize
    end
    count
  end


  def self.smallest_array_size(one, two)
    if one.count <= two.count
      one.count
    else
      two.count
    end
  end

end
