#!/usr/bin/ruby

class Complement
  def self.of_rna(inputrna)
    rnatodna = { "C" => "G", "G" => "C", "A" => "T", "U" => "A" }
    result = ""
    inputrna.split("").each do |i|
      result = result + rnatodna[i]
    end
  return result
  end

  def self.of_dna(inputdna)
    dnatorna = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
    result = ""
    inputdna.split("").each do |i|
      result = result + dnatorna[i]
    end
  return result
  end
end
