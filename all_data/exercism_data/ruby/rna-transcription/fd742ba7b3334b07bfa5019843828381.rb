class Complement
  DIC = {'G' => 'C',
         'C' => 'G',
         'T' => 'A',
         'A' => 'U'}

  def self.of_dna(dna)
    convert(dna, DIC)
  end

  def self.of_rna(rna)
    convert(rna, DIC.invert)
  end

  protected
    def self.convert(value, dic)
      value = value.scan /\w/
      result = ''

      value.each { |x| result += dic[x] }
      result
    end
end

