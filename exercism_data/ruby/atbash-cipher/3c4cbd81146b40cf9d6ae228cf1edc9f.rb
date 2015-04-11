# class Atbash
class Atbash
  ATOZ = ('a'..'z').to_a

  def self.cipher(k)
    i = ATOZ.index(k)
    i.nil? ? k : ATOZ[-1 * (ATOZ.index(k) + 1)]
  end

  def self.encode(str)
    str
    .gsub(/[^0-9A-Za-z]/, '') # Removal of special characters
    .each_char
    .map { |chr| cipher(chr.downcase) } # forming string of ciphers
    .join
    .gsub(/(\w{5})/, '\1 ').strip  # grouping them in chars of 5
  end
end
