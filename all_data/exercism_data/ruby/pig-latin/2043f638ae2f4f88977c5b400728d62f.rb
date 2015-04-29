class PigLatin

  def self.translate(str)
    consts = str.chars.each_with_object([]) do |el, arr|
      el.scan(/[aeiou]/).size > 0 ? (break arr) : (arr << el)
    end
    if consts
      results = str[consts.size..-1]
      results << consts.join
    end
    results += "ay"
  end
end
