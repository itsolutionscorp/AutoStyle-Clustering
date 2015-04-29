class Complement



  def self.of_dna(x)
    n1 = x.gsub("A", "7")
    n2 = n1.gsub("T", "A")
    n3 = n2.gsub("7", "U")
    n4 = n3.gsub("G", "3")
    n5 = n4.gsub("C", "G")
    n6 = n5.gsub("3", "C")
  end

  def self.of_rna(x)
    n1 = x.gsub("A", "7")
    n2 = n1.gsub("U", "A")
    n3 = n2.gsub("7", "T")
    n4 = n3.gsub("G", "3")
    n5 = n4.gsub("C", "G")
    n6 = n5.gsub("3", "C")
  end

end
