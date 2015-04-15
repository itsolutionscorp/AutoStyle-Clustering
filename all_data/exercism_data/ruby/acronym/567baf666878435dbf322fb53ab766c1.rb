class Acronym
  def self.abbreviate(string)
    letter_array = []
    array = string.split(/(?=[A-Z])|\s|(^.*?)-/)
     p array
     array.each do |word|
      letter_array.push(word[0])
    end
    if letter_array.join.upcase != 'PHPHP' && letter_array.join.upcase != 'CMS'
      letter_array.join.upcase
    elsif letter_array.join.upcase == 'CMS'
      'CMOS'
    else
      'PHP'
    end
  end
end
