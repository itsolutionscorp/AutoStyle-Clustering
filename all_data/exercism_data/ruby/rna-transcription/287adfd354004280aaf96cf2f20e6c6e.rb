class Complement
  def self.of_dna(x)
    raise ArgumentError unless x.downcase =~ /^[?gcta]*$/
    x.downcase.tr('gcta', 'cgau').upcase
  end
  
  def self.of_rna(x)
    raise ArgumentError unless x.downcase =~ /^[?cgau]*$/
    x.downcase.tr('cgau', 'gcta').upcase
  end
end
