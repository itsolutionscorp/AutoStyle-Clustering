class Hamming

  def self.compute(strand1, strand2)
    strand1 = strand1.split('')
    strand2 = strand2.split('')

    strand1.zip(strand2).count do |string1, string2|
      self.substitution?(string1, string2)
    end
  end

  def self.substitution?(string1, string2)
    if string1 && string2
      string1 != string2
    end
  end

end
