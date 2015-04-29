class Complement
  class << self
    %w(dna rna).each do |type|
      define_method "of_#{type}" do |data|
        data.split(//).map { |item| send("#{type}_hash")[item] }.join
      end
    end

    def dna_hash
      { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
    end

    def rna_hash
      dna_hash.invert
    end
  end
end
