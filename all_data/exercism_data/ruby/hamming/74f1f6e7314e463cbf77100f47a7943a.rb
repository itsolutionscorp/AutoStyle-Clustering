class Hamming < Struct.new(:strand1, :strand2)
  def self.compute(strand1, strand2)
    new(strand1, strand2).compute
  end

  def compute
    non_matching_charsets.count
  end

  private

  def non_matching_charsets
    charsets.select { |charset| non_matching_charset?(charset) }
  end

  def charsets
    strand1.split('').zip(strand2.split(''))
  end

  def non_matching_charset?(charset)
    neither_character_is_nil?(charset) && characters_do_not_match?(charset)
  end

  def neither_character_is_nil?(charset)
    !charset.include?(nil)
  end

  def characters_do_not_match?(charset)
    charset[0] != charset[1]
  end
end
