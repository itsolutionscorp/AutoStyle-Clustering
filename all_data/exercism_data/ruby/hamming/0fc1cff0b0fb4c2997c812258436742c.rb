class Hamming

  def self.compute (strand1, strand2)
    arr1 = strand1.split('')
    arr2 = strand2.split('')
    arr1.zip(arr2).inject(0) do |count, pair|
      pair[0] == pair[1] ? count : count + 1
    end 
  end

end
