class PigLatin
  def self.translate(phrase)
    phrase.downcase.split(' ').map do |word|
      return (word + "ay") if word.match /\A(y[^aeiou]|xr|[aeiou])/
      s,r = word.scan(/\A([^aeiou]?qu|[^aeiou]+)(.*)/).first
      r+s+"ay"
    end.join(' ')
  end
end
