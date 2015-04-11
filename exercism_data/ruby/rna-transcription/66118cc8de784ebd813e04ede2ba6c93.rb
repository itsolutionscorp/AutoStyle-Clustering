class Complement

  def self.of_dna(strand)

    if strand ==  'C'
       strand = 'G'
    elsif strand == 'G'
      strand = 'C'
    elsif  strand == 'T'
      strand = 'A'
    elsif strand == 'A'
      strand = 'U'
    end



  end

end
