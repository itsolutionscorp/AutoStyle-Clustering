class Hamming

  def self.compute a,b
    count = 0

    zipped = a.chars.zip(b.chars)
    zipped.each {|pair| count += 1 if mutation?(pair) }

    count
  end

  def self.mutation? nucleic_pair
   nucleic_acid_a = nucleic_pair.first
   nucleic_acid_b = nucleic_pair[1]
   nucleic_acid_b ||= nucleic_acid_a
   difference = (nucleic_acid_a <=> nucleic_acid_b).abs

   difference != 0
  end

end
