# Inspired by http://programming.ianwhitney.com/blog/2014/12/26/exercism-the-rna-transcription-exercise/
require_relative 'DNA/Nucleotides/nucleotide'
require_relative 'DNA/Nucleotides/adenine'
require_relative 'DNA/Nucleotides/cytosine'
require_relative 'DNA/Nucleotides/guanine'
require_relative 'DNA/Nucleotides/thymidine'

require_relative 'RNA/Nucleotides/nucleotide'
require_relative 'RNA/Nucleotides/adenine'
require_relative 'RNA/Nucleotides/cytosine'
require_relative 'RNA/Nucleotides/guanine'
require_relative 'RNA/Nucleotides/uracil'

require_relative 'DNA/dna'
require_relative 'RNA/rna'

class Complement
  def self.of_dna strand
    DNA::DNA.complement(strand)
  end

  def self.of_rna strand
    RNA::RNA.complement(strand)
  end
end
