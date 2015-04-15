class Hamming
  def self.compute(seq_a,seq_b)
    raise ArgumentError.new('Sequences must be of the same length') unless seq_a.length == seq_b.length
    distance = 0
    seq_a.chars.zip(seq_b.chars).each do |char_a,char_b|
      distance +=1 if char_a != char_b
    end
    return distance
  end
end
