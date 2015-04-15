class String
  def upper?
    /[[:upper:]]/.match(self)
  end

  def separator?
    /[\s\-]/.match(self)
  end
end

class Acronym
  def self.abbreviate(name)
    name_chars = name.chars
    name_shift = [nil] + name_chars
    char_pairs = name_shift.zip(name_chars)
    char_pairs.collect { |c1, c2| c2.upcase if initial?(c1, c2) }.join("")
  end

  private

  def self.initial?(c1, c2)
    c1.nil? || c1.separator? || (!c1.upper? && !c2.nil? && c2.upper?)
  end
end
