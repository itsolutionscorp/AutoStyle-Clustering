class Atbash

  KEY = Hash[('a'..'z').zip(('a'..'z').to_a.reverse)]

  def self.encode(value)
    value.downcase.chars.map { |char| determine_encoded_char(char) }.join.gsub(/(.{5})/, '\1 ').strip
  end

  private
  def self.determine_encoded_char(char) 
    KEY.fetch(char) { |char| char.match(/\d/) ? char : nil}
  end

end
