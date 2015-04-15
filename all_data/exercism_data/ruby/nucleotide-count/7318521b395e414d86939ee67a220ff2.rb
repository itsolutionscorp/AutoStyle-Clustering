class Nucleotide
  HISTOGRAM = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }
  
  def initialize string
    @string = string
  end
    
  def self.from_dna string
   raise ArgumentError, 'Invalid Argument.' if !(string =~ /^$|^[AGTC]+$/)
    new string
  end
  
  def count char
    @string.chars.count char
  end
  
  def histogram
    HISTOGRAM.each do |key, value|
      HISTOGRAM[key] = count key
    end
  end
end
