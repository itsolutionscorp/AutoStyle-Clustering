class Hamming

  def self.compute a,b
    count = 0
    a.each_nucleic_acid do |nucleic_acid,index|
      their_nucleic_acid = b[index]
      count += 1 if mutation?(nucleic_acid, their_nucleic_acid)
    end
    count
  end

  def self.mutation? nucleic_acid_a, nucleic_acid_b
   nucleic_acid_b ||= nucleic_acid_a
   difference =  (nucleic_acid_a <=> nucleic_acid_b).abs
   difference != 0
  end
end

class String
  def each_nucleic_acid &block
    i = 0
    self.length.times do
      yield  self[i],i if block_given?
      i += 1
    end
  end
end
