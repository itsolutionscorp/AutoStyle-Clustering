class Complement

  def self.of_dna(x)
    new = []
    x.length.times do |n|
      case x[n]
      when "C"
        new << "G"
      when "G"
        new << "C"
      when "T"
        new << "A"
      when "A"
        new << "U"
      end
    end
    new.join
  end

  def self.of_rna(x)
    new = []
    x.length.times do |n|
      case x[n]
      when "C"
        new << "G"
      when "G"
        new << "C"
      when "A"
        new << "T"
      when "U"
        new << "A"
      end
    end
    new.join
  end
end
