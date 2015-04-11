class Complement
  def self.of_dna(arg)
    arg = arg.split("")
    result = ''
    arg.each do |elem| 
      case elem
      when "C"
        result += "G"
      when "G"
        result += "C"
      when "A"
        result += "U"
      when "T"
        result += "A"
      end
    end
    result
  end

  def self.of_rna(arg)
    arg = arg.split("")
    result = ''
    arg.each do |elem| 
      case elem
      when "C"
        result += "G"
      when "G"
        result += "C"
      when "U"
        result += "A"
      when "A"
        result += "T"
      end
    end
    result
  end
end
