class Acronym
  def self.abbreviate(long_name)
    long_name.gsub(/([a-z])([A-Z])/, '\1 \2') \
      .gsub(/\W/, ' ') \
      .split(' ') \
      .map { |word| word[0].upcase } \
      .join
  end
end
