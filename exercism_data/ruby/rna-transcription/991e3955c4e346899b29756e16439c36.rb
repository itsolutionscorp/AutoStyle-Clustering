class DNA
  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    @sequence.chars.each_with_object('') { |c, s|
      c == 'T' ?  s << 'U' :  s << c
    }
  end
end
