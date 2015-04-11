class Acronym

  def self.abbreviate(str)
    words = str.scan(/[A-Z]+[a-z]*|[a-z]+/)
    words.map{|word| word[0]}.join.upcase
  end

end
