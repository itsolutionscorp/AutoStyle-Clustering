module PigLatin
  def self.translate phrase
    phrase.gsub(/\w+/) {|word| translate_word word }
  end

  def self.translate_word input
    case input
    when /^[aeiou]/,
         /^[yx][^aeiou]/
      input + "ay"
    when /^([^aoeui]*qu)(.*)$/,
         /^([^aoeui]+)(.*)$/
      $2 + $1 + "ay"
    else
      input
    end
  end
end
