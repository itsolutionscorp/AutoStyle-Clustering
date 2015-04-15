class PigLatin
  VOWELS = %w(a u i e o y)
  VCLASS = /[#{VOWELS.join}]/
  CONSONNANTS = [*'a'..'z'] - VOWELS
  CCLASS = /[#{CONSONNANTS.join}]/

  def self.translate word
    word.split.map(&method(:match_word)).join(' ')
  end

  private

  def self.match_word word
    if VOWELS.any? { |v| v == word[0] } || word[0..1] =~ /xr/
      if word[0..1] =~ /ye/
        word[1..-1] + word[0]
      else
        word
      end
    else
      match = word.match(/^(s?qu|[#{CONSONNANTS.join}]+)(.*)/)
      match[2] + match[1]
    end + 'ay'
  end
end
