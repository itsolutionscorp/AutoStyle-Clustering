class Complement

#takes dna and makes rna complement

  def self.of_dna(strands)
    strands.chars.map do |n|
      case
        when n == 'G'
           'C'
        when n == 'T'
           'A'
        when n == 'C'
           'G'
        when n == 'A'
           'U'
        else
          raise ArgumentError
      end
    end.join

  end

#takes rna and makes dna complement

  def self.of_rna(strands)
    strands.chars.map do |n|
      case
        when n == 'G'
           'C'
        when n == 'A'
           'T'
        when n == 'C'
           'G'
        when n == 'U'
           'A'
        else
          raise ArgumentError
      end
    end.join
  end

end


# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`
