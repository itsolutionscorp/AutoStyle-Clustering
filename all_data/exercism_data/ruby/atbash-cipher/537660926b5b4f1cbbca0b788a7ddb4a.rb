module Atbash
  A_PLUS_Z_ORDINAL = 'a'.ord + 'z'.ord

  def self.encode(string)
    string.downcase
          .gsub(/[^a-z1-9]+/, '')
          .gsub(/[a-z]/) { |char| (A_PLUS_Z_ORDINAL - char.ord).chr }
          .scan(/.{1,5}/)
          .join(" ")
  end
end
