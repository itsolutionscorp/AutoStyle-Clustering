class PigLatin
  def self.translate(s)
    res = ""
    r1 = /^([aeiou]|yt|xr)[a-z]+$/
    r2 = /^(thr?|sch|ch|qu|[^aeiou](qu)?)([a-z]+)$/
    s.downcase.split.each do |word|
      case word
      when r1
        res << "#{word}ay "
      when r2
        lm = Regexp.last_match
        res << "#{lm[3]}#{lm[1]}ay "
      else
        raise ArgumentError
      end
    end
    res.rstrip
  end
end
