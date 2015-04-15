module Complement

  class Transcriber
    def initialize(input, output)
      @input  = input
      @output = output
    end
    def transcribe(str)
      str.tr(@input, @output)
    end
  end

  @@rna_producer = Transcriber.new('ACGT', 'UGCA')
  @@dna_producer = Transcriber.new('ACGU', 'TGCA')

  def self.of_dna(dna)
    @@rna_producer.transcribe(dna)
  end
  def self.of_rna(rna)
    @@dna_producer.transcribe(rna)
  end
end
