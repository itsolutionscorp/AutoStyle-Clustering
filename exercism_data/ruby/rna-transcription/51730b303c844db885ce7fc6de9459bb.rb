class Complement
  TABLE = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(str)
    convert(str , TABLE)
  end

  def self.of_rna(str)
    convert(str , TABLE.invert)
  end

  private
  def self.convert(str , table)
    str.chars.inject([]){|ary , s| ary << table[s]}.join
  end
end
