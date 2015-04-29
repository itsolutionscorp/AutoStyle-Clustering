class Atbash
  #Initialize Hash
  alphabet = ('a'..'z').to_a
  opposite = alphabet.reverse
  KEY = Hash[alphabet.zip(opposite)]

  def self.encode(string)
    array = string.downcase.chars.map do |char|
      if char =~ /\w/
        KEY[char] ? KEY[char] : char
      end
    end.compact

    array.each_slice(5).to_a.map {|slice| slice.join}.join(" ")
  end



end
