class Acronym
  def self.abbreviate(str)
    str.split(/\s|-|(?=[A-Z][a-z])/).map {|word| word[0].upcase}.join
  end
end
