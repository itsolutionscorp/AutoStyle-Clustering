class Complement
  DNA_MAP = { 'T' => 'A', 'C' => 'G', 'G' => 'C', 'A' => 'U' }
  RNA_MAP = DNA_MAP.invert

  class << self
    [:dna, :rna].each do |type|
      define_method("of_#{type}") do |strand|
        map = const_get("#{type.upcase}_MAP")
        strand.split('').collect { |n| map[n] || (raise ArgumentError) }.join
      end
    end
  end
end
