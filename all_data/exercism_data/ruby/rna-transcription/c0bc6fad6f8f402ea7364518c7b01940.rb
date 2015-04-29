class Complement
  def self.of_dna(a_strand)
    a_strand.each_char.inject('') do |strand, n|
      strand << case n
                when 'G' then 'C'
                when 'C' then 'G'
                when 'T' then 'A'
                when 'A' then 'U'
                else
                  raise ArgumentError
                end
    end
  end

  def self.of_rna(a_strand)
    a_strand.each_char.inject('') do |strand, n|
      strand << case n
                when 'G' then 'C'
                when 'C' then 'G'
                when 'A' then 'T'
                when 'U' then 'A'
                else
                  raise ArgumentError
                end
    end
  end
end
