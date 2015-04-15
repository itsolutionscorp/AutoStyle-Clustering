class Hamming

  def self.compute(str1, str2)
    strand1 = str1.split('')
    strand2 = str2.split('')

    return 0 if strand1 == strand2

    if strand1.length > strand2.length
      filler = strand1.length - strand2.length
      filler.times do 
        strand2 << 'X'
      end
    end
    distance = 0
    strand1.each_with_index do |nuc_one, index|
      return 0 if strand2[index] == 'X' || nuc_one == 'X'
      nuc_one == strand2[index] ? (distance += 0) : (distance += 1)
    end
    distance
  end
end
