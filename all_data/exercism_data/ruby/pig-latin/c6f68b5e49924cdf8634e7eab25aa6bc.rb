class PigLatin
  def self.translate(phrase)
    phrase.split(' ').map { |t| self.translate_term(t) }.join(' ')
  end

  protected
  def self.translate_term(term)
    if term[0].vowel?
      term + 'ay'
    else
      chars = term.chars
      header = []
      header << chars.shift while !chars[0].vowel? || (header[-1] + chars[0] == 'qu')
      chars.join + header.join + 'ay'
    end
  end
end

class String
  def vowel?
    ['a','e','i','o','u'].include? self
  end
end
