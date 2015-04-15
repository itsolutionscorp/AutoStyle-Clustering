module Acronym
  def self.abbreviate long_name
    long_name.scan(/[A-Z]+[a-z]*|[a-z]+/).map(&:chr).join.upcase
  end
end
