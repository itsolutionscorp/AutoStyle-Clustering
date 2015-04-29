#!/usr/bin/ruby

class Complement
  @@rnatodna = { "C" => "G", "G" => "C", "A" => "T", "U" => "A" }
  @@dnatorna = @@rnatodna.invert
  
  def self.of_rna(inputrna)
    return complementhelper(inputrna, @@rnatodna)
  end

  def self.of_dna(inputdna)
    return complementhelper(inputdna, @@dnatorna)
  end

  def self.complementhelper(inputstrand, inputhash)
    inputstrand.chars.map {|c| inputhash[c] }.join
  end 
end
