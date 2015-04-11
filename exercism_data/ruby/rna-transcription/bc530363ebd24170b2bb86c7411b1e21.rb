class Complement

  def self.of_rna(string)
    str_arr = string.split("")
    comp_arr = []
    str_arr.each do |el|
      if el == "G"
        comp_arr.push("C")
      elsif el == "C"
        comp_arr.push("G")
      elsif el == "U"
        comp_arr.push("A")
      else
        comp_arr.push("T")
      end
    end
    return comp_arr.join()
  end

  def self.of_dna(string)
    str_arr = string.split("")
    comp_arr = []
    str_arr.each do |el|
      if el == "G"
        comp_arr.push("C")
      elsif el == "C"
        comp_arr.push("G")
      elsif el == "T"
        comp_arr.push("A")
      else
        comp_arr.push("U")
      end
    end
    return comp_arr.join()
  end

end
