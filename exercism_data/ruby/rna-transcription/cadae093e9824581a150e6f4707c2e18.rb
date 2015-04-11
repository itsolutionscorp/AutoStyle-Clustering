class Complement
  
  MAP = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  
  def self.of_dna(arg)
    check arg.chars.map(MAP[&:])
  end

  def self.of_rna(arg)
    check arg.chars.map(MAP.invert[&:])
  end
 
  def self.check(arg)
  	raise ArgumentError if arg.include? nil
  	arg.join
  end
end
