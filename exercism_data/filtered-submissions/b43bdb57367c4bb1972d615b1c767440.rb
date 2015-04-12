class Hamming

  def compute(strand1, strand2)

    count  = 0

    (0..strand1.length).each do |n|
      if strand1[n] != strand2[n]
        count+=1
      end
    end

    return count

  end
end
