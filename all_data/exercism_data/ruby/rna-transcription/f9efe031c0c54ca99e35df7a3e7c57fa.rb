require 'pry'
class Complement
  def of_dna(string1)
    out = ""
    comp = {'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U'}
    string1.length.times do |index|
      out = out + comp[string1[index]]
    end
    out
  end
  def of_rna(string1)
    out = ""
    comp = {'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'T', 'U' => 'A'}
    string1.length.times do |index|
      out = out + comp[string1[index]]
    end
    #binding.pry
    out
  end
end
