class Acronym
  def self.abbreviate(long_name)
    long_name
      .gsub(/([a-z])([A-Z])|\W/, '\1 \2')
      .split(' ')
      .map { |word| word[0] }
      .join
      .upcase
  end
end
