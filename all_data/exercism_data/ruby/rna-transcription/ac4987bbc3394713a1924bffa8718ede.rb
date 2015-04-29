require 'benchmark/ips'

class Complement

  DNA_TO_RNA =  {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

  RNA_TO_DNA = DNA_TO_RNA.invert

  class << self

    def of_dna(dna_str)
      dna_str.gsub(/[#{DNA_TO_RNA.keys.join}]/, DNA_TO_RNA)
    end

    def of_rna(rna_str)
      rna_str.gsub(/[#{RNA_TO_DNA.keys.join}]/, RNA_TO_DNA)
    end

    def of_dna2(dna_str)
      dna_str.each_char.map {|char| DNA_TO_RNA[char]}.join
    end

    def benchmark

      Benchmark.ips do |x|
        x.report('dna_convert_with_gsub') do |times|
          i = 0
          while i < times
            Complement.of_dna('ACGTGGTCTTAA')
            i += 1
          end
        end

        x.report('dna_convert_with_map_join') do |times|
          i = 0
          while i < times
            Complement.of_dna2('ACGTGGTCTTAA')
            i += 1
          end
        end

        x.compare!

      end

    end
  end
end
