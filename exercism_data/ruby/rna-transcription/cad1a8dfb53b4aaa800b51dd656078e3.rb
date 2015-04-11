class Complement
  class << self
    @dna_map = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }

    %w(dna, rna).each do |type|
      curr_map = type == 'dna' ? @dna_map : @dna_map.invert

      define_method("of_#{type}") do |strand|
        fail ArgumentError if !strand.match(/#{curr_map.keys.join('|')}/)

        comp = ''

        strand.each_char do |nuc|
          comp << curr_map[nuc]
        end

        comp
      end
    end
  end
end
