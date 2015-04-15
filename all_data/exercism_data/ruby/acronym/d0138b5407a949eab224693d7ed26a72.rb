class Acronym
  def self.abbreviate(string)
    Acronym.new(string).acronym
    #string.scan(/\b+([a-zA-Z])|([A-Z])/).flatten.compact.join.upcase
  end

  def initialize(string)
    @string = string
  end

  def acronym
    @acronym = @string.scan(/\b+([a-zA-Z])|([A-Z])/).flatten.compact.join.upcase
    return recursive_scan if recursive?
    @acronym
  end

  def recursive?
    recursive_scan.size > 0 && @acronym.include?(recursive_scan)
  end

  def recursive_scan
    @string.scan(/[A-Z]{2,}+/).join
  end

end
