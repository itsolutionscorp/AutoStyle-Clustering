class PigLatin
  def PigLatin.translate str
    str.split.map{|s| PigLatin.translate_one(s)}.join(" ")
  end
  def PigLatin.translate_one str
    if /^([bcdfghlklmnpqrstvwxyz]qu|thr|sch)/ =~ str
      str[3...str.size] + str[0..2] + "ay"
    elsif /^(ch|qu|th)/ =~ str
      str[2...str.size] + str[0..1] + "ay"
    elsif /^(ye|[bcdfghlklmnpqrstvwz]|[xy][aeiou])/ =~ str
      str[1...str.size] + str[0] + "ay"
    else
      str + "ay"
    end
  end
end
