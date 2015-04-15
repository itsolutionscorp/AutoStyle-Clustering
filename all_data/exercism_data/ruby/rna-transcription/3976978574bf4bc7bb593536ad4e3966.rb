class Complement
  @DNA_TO_RNA = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}

  def self.of_dna(i)
    temp = String.new
    i.split(//).each{|z| temp << @DNA_TO_RNA.fetch(z)}
    temp
  end

  def self.of_rna(i)
    temp = String.new
    i.split(//).each{|z| temp << @DNA_TO_RNA.key(z)}
    temp
  end
end
