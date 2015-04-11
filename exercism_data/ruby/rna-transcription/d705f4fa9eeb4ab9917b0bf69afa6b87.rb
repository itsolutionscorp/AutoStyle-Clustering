class Complement
  TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  TO_DNA = TO_RNA.invert

  %w(dna rna).each do |acid_name|
    other_acid = (acid_name == 'dna' ? 'rna' : 'dna')

    a_new_class = Class.new(Object) do
      attr_reader :strand

      def initialize(strand)
        @strand = strand
      end

      define_method :"to_#{other_acid}" do
        other_class  = Complement.const_get(other_acid.upcase)
        transcriptor = Complement.const_get("TO_#{other_acid.upcase}")
        other_class.new(@strand.chars.map { |n| transcriptor[n] }.join)
      end
    end
    const_set(acid_name.upcase, a_new_class)

    acid_class = Complement.const_get(acid_name.upcase)
    define_singleton_method :"of_#{acid_name}" do |strand|
      acid_class.new(strand).send(:"to_#{other_acid}").strand
    end
  end
end
