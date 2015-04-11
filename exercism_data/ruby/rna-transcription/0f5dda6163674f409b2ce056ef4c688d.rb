class Complement
  def self.of_dna(input)
    output = String.new
    (0...input.length).each do |nucleotide|
      case input[nucleotide]
        when 'G'
	  output << 'C'
	when 'C'
	  output << 'G'
	when 'T'
	  output << 'A'
	when 'A'
	  output << 'U'
      end
    end
  output
  end

  def self.of_rna(input)
    output = String.new
    (0...input.length).each do |nucleotide|
      case input[nucleotide]
	when 'C'
	  output << 'G'
	when 'G'
	  output << 'C'
	when 'A'
	  output << 'T'
	when 'U'
	  output << 'A'
      end
    end
    output
  end
end
