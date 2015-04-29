class Complement
  DNA_TO_RNA = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(arg)
    do_mapping(DNA_TO_RNA, arg)
  end

  def self.of_rna(arg)
    do_mapping(DNA_TO_RNA.invert, arg)
  end

  private

  def self.do_mapping(mapping, arg)
    arg.chars.reduce('') do |a, e|
      a + mapping[e]
    end
  end
end
