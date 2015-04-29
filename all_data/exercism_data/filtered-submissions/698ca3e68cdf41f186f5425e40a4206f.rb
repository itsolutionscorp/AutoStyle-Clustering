class Hamming
  def Hamming.compute (strand1, strand2)
    strand1 = strand1.split('')
    strand2 = strand2.split('')
    hamdist = 0

    strand1.each_with_index do |val, ind|
      if val != strand2[ind] && strand2[ind] != nil
        hamdist += 1
      end
    end
    hamdist
  end
end
