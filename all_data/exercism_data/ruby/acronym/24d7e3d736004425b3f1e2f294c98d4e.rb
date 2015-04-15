class Acronym
  def self.abbreviate(sentence)
    result = ''
     sentence.scan(/[A-Z]+[a-z]*|[a-z]+/) do |word|
      result += word.chr.upcase
    end
    return result
  end

end
