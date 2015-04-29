class PigLatin
  VOWELS = %w(a e i o u yt xr)
  CONSONANTS = [*'a'..'z'] - VOWELS

  attr_reader :str

  def self.translate str
    PigLatin.new(str).latinize
  end

  def initialize str
    @str = str.downcase
  end

  def latinize
    str.split(' ').each_with_object([]) do |w, a|
      r = VOWELS.any? { |v| w.start_with? v } ? vowel_sub(w) : consonant_sub(w)
      a << r
    end.join(' ')
  end

  private

  def vowel_sub w
    w + 'ay'
  end

  def consonant_sub w
    if %w(sch squ thr).any? { |ccc| w.start_with? ccc }
      w[3..-1] + w[0..2]
    elsif %w(ch qu th).any? { |cc| w.start_with? cc }
      w[2..-1] + w[0..1]
    else
      w[1..-1] + w[0]
    end + 'ay'
  end
end
