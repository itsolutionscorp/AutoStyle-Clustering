class Complement

  RNA = { complementees: "CGTA", complementers: "GCAU" }

  DNA = { complementees: "CGUA", complementers: "GCAT" }

  def self.of_dna(letters)
    letters.tr(RNA[:complementees], RNA[:complementers])
  end

  def self.of_rna(letters)
    letters.tr(DNA[:complementees], DNA[:complementers])
  end
end
