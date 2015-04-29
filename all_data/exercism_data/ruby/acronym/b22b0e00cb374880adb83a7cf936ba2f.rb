class Acronym

def self.abbreviate(string)
  string = string.split(":").first
  array = string.split(/(?=[A-Z])|\s|-/)
  capitals = array.map do |word|
    word[0].upcase
  end
  capitals.join
end

end
