class Complement
  @common_mapping = { 'C' => 'G', 'G' => 'C' }

  def self.of_dna(arg)
    mapping = @common_mapping.merge('T' => 'A', 'A' => 'U')
    do_mapping(mapping, arg)
  end

  def self.of_rna(arg)
    mapping = @common_mapping.merge('U' => 'A', 'A' => 'T')
    do_mapping(mapping, arg)
  end

  private

  def self.do_mapping(mapping, arg)
    arg.chars.reduce('') do |a, e|
      a + mapping[e]
    end
  end
end
