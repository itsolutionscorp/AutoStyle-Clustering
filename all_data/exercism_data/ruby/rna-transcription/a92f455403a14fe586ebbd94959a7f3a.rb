class Complement
  
  def self.of_dna(x)
    results = []
    (x.split("")).each do |y|
      case
        when y == "G"
          results << "C"
        when y == "C"
          results << "G"
        when y == "T"
          results << "A"
        when y == "A"
          results << "U"
        else
      end
    end
    results.join
  end
  
  def self.of_rna(x)
    results = []
    (x.split("")).each do |y|
      case
        when y == "G"
          results << "C"
        when y == "C"
          results << "G"
        when y == "A"
          results << "T"
        when y == "U"
          results << "A"
        else
      end
    end
    results.join
  end
end
