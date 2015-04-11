class Hamming
  def self.compute(strand1, strand2)
      if strand1.eql?strand2
        return 0
      else
        difference strand1, strand2
      end
  end

  def self.difference(strand1, strand2)
      i = 0
      size = strand1.length
      counter = 0

      while i < size
        if !strand1[i].eql?strand2[i]
          counter += 1
        end
        i += 1
      end
      return counter
  end

end
